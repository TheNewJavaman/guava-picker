package net.javaman.guavapicker.sql

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

open class AEntity(id: EntityID<ULong>, table: ATable) : Entity<ULong>(id) {
    var createdAt by table.createdAt
    var updatedAt by table.updatedAt
}

open class AEntityClass<_Entity : AEntity>(table: ATable) : EntityClass<ULong, _Entity>(table)