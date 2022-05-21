package com.example.fiit

object Constants {

    fun defaultExerciseList():ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val jumpingJacks = ExerciseModel(
            id = 1,
            name = "Jumping Jacks",
            image = R.drawable.ic_jumping_jacks,
            isCompleted = false,
            isSelected = false

        )
        exerciseList.add(jumpingJacks)

        val abdominalCrunch = ExerciseModel(
            id = 2,
            name = "Abdomimal Crunch",
            image = R.drawable.ic_abdominal_crunch,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(abdominalCrunch)

        val highKneesRunningInPlace = ExerciseModel(
            id = 3,
            name = "High Heels Running In Place",
            image = R.drawable.ic_high_knees_running_in_place,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(highKneesRunningInPlace)

        val lunge = ExerciseModel(
            id = 4,
            name = "Lunges",
            image = R.drawable.ic_lunge,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(lunge)

        val plank = ExerciseModel(
            id=5,
            name = "Planks",
            image = R.drawable.ic_plank,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(plank)

        val pushUps = ExerciseModel(
            id = 6,
            name = "PushUps",
            image = R.drawable.ic_push_up,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pushUps)

        val squat = ExerciseModel(
            id = 7,
            name = "Squats",
            isCompleted = false,
            isSelected = false,
            image = R.drawable.ic_plank
        )
        exerciseList.add(squat)

        val stepUpOntoChair  = ExerciseModel(
            id = 8,
            name = "Step Onto Chair",
            image = R.drawable.ic_step_up_onto_chair,
            isSelected = false,
            isCompleted = false

        )
        exerciseList.add(stepUpOntoChair)

        val tricepsDipOnChair = ExerciseModel(
            id = 9,
            name = "TricepsDipsOnChair",
            image = R.drawable.ic_triceps_dip_on_chair,
            isSelected = false,
            isCompleted = false

        )
        exerciseList.add(tricepsDipOnChair)

        val wallSit = ExerciseModel(
            id = 10,
            name = "Wall Sit",
            image = R.drawable.ic_wall_sit,
            isSelected = false,
            isCompleted = false
        )
        exerciseList.add(wallSit)

        val sidePlank = ExerciseModel(
            id = 11,
            image = R.drawable.ic_side_plank,
            name = "Side Plank",
            isSelected = false,
            isCompleted = false
        )
        exerciseList.add(sidePlank)

        val pushUpAndRotation = ExerciseModel(
            id = 12,
            name = "Push Up and Rotation",
            image = R.drawable.ic_push_up_and_rotation,
            isSelected = false,
            isCompleted = false
        )
        exerciseList.add(pushUpAndRotation)

        return exerciseList
    }
}