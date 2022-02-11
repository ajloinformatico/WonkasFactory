package es.infolojo.wonkasfactory.data

import es.infolojo.wonkasfactory.data.bo.WonkaWorkerBO
import es.infolojo.wonkasfactory.data.remote.dto.WonkaWorkerDTO
import es.infolojo.wonkasfactory.data.vo.WonkaWorkerVO

object Mappers {

    fun List<WonkaWorkerDTO>.toBo(): List<WonkaWorkerBO> =
        this.map { it.toBO() }
    
    fun WonkaWorkerDTO.toBO(): WonkaWorkerBO =
        WonkaWorkerBO(
            id = this.id.orEmpty(),
            firstName = this.firstName.orEmpty(),
            lastName = this.lastName.orEmpty(),
            description = this.description.orEmpty(),
            image = this.image.orEmpty(),
            profession = this.profession.orEmpty(),
            quota = this.quota.orEmpty(),
            country = this.country.orEmpty(),
            height = this.height.toString(),
            age = this.age.toString(),
            genre = this.genre.orEmpty(),
            email = this.email.orEmpty(),
            color = this.favorite?.color.orEmpty(),
            food = this.favorite?.food.orEmpty(),
    )

    fun WonkaWorkerBO.toVo(): WonkaWorkerVO =
        WonkaWorkerVO(
            id = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            description = this.description,
            image = this.image,
            profession = this.profession,
            quota = this.quota,
            country = this.country,
            height = this.height,
            age = this.age,
            genre = this.genre,
            email = this.email,
            color = this.color,
            food = this.food,
        )
}

