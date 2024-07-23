package example.com.repository

interface CrudRepository <T, I> {

    suspend fun findAll(): List<T>

    suspend fun findById(id: I): T?

    suspend fun save(entity: T): T

    suspend fun update(entity: T): T

    suspend fun deleteById(id: I)

}
