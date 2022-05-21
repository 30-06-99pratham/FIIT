package com.example.fiit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fiit.databinding.ActivityExerciseBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding : ActivityExerciseBinding? = null

    private var tts : TextToSpeech?=null

    private var restTimer:CountDownTimer? = null
    private var restProgress = 0
    private var restTimerDuration:Long = 1
    private var exerciseTimerDuration : Long =1

    private var ExerciseTimer:CountDownTimer?=null
    private var ExerciseProgress = 0

    private var exerciseList : ArrayList<ExerciseModel>?= null
    private var currentExercisePosition= -1

    private var exerciseAdapter : ExerciseStatusAdapter? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        exerciseList = Constants.defaultExerciseList()

        tts = TextToSpeech(this,this)

        binding?.toolbarExercise?.setNavigationOnClickListener{
            onBackPressed()
        }

        setUpRestView()
        setUpExerciseStatusRecyclerView()
    }

    private fun setUpExerciseStatusRecyclerView(){
        binding?.rvExerciseStatus?.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.rvExerciseStatus?.adapter= exerciseAdapter
    }

    private fun setUpRestView(){
        binding?.ExerciseProgressBar?.visibility = View.INVISIBLE
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        binding?.tvUpcomingLabel?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE

        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        binding?.tvUpcomingExerciseName?.text = exerciseList!![currentExercisePosition+1].getName()
        setRestProgressbar()

    }
    private fun setRestProgressbar(){
        binding?.progressBar?.progress = restProgress
        restTimer = object:CountDownTimer(restTimerDuration*10000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }
            override fun onFinish() {
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setUpExerciseView()
            }
        }.start()
    }
    private fun setUpExerciseView(){

        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE
        binding?.tvUpcomingLabel?.visibility = View.INVISIBLE

        if(ExerciseTimer!=null){
            ExerciseTimer?.cancel()
            ExerciseProgress=0
        }
        speakOut(exerciseList!![currentExercisePosition].getName())
        setExerciseProgressbar()

        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text=exerciseList!![currentExercisePosition].getName()
    }

    private fun setExerciseProgressbar(){
        binding?.ExerciseProgressBar?.progress = ExerciseProgress
        ExerciseTimer = object:CountDownTimer(exerciseTimerDuration*30000, 1000){
            override fun onTick(p0: Long) {
                ExerciseProgress++
                binding?.ExerciseProgressBar?.progress = 30 - ExerciseProgress
                binding?.tvExerciseTimer?.text = (30 - ExerciseProgress).toString()
            }
            override fun onFinish() {
                exerciseList!![currentExercisePosition].setIsSelected(false)
                exerciseList!![currentExercisePosition].setIsCompleted(true)
                exerciseAdapter!!.notifyDataSetChanged()

                if(currentExercisePosition<exerciseList!!.size-1){
                    setUpRestView()
                }
                else{
                    finish()
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)

                }
            }
        }.start()

    }
    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        if(ExerciseTimer!=null){
            ExerciseTimer?.cancel()
            ExerciseProgress=0
        }
        if(tts!=null){
            tts?.stop()
            tts?.shutdown()
        }
        binding = null
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts?.setLanguage(Locale.US)
            if(result==TextToSpeech.LANG_NOT_SUPPORTED||
                    result==TextToSpeech.LANG_MISSING_DATA){
                Log.e("TTS","The Language Specified is not supported")
            }
        }
        else{
            Log.e("TTS","Initialization Failed")
        }
    }

    private fun speakOut(text:String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
}
