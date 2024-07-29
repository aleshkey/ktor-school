package example.com.repository

interface CrudRepository <T, I> {

    fun findAll(): List<T>

    fun findById(id: I): T?

    fun save(entity: T): T

    fun update(entity: T): T

    fun deleteById(id: I)

}
