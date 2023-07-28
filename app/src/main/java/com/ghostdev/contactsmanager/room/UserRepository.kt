package com.ghostdev.contactsmanager.room

class UserRepository(private val dao: UserDao) {

    val users = dao.getAllUsersInDB()

    suspend fun insert(user: User): Long {
        return dao.insertUser(user)
    }

    suspend fun delete(user: User) {
        return dao.deleteUser(user)
    }

    suspend fun update(user: User) {
        return dao.updateUser(user)
    }

    suspend fun deleteAll() {
        return dao.deleteAll()
    }
}