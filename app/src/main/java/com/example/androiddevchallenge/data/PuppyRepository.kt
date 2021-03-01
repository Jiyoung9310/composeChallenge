package com.example.androiddevchallenge.data

class PuppyRepository private constructor(private val puppyDao: PuppyDao) {

    fun getPuppies() = puppyDao.getPuppies()

    fun getPuppy(puppyId: Int) = puppyDao.getPuppy(puppyId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PuppyRepository? = null

        fun getInstance(puppyDao: PuppyDao) =
            instance ?: synchronized(this) {
                instance ?: PuppyRepository(puppyDao).also { instance = it }
            }
    }
}
