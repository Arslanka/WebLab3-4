package com.example.arslanka.weblab4.services

import com.example.arslanka.weblab4.models.Shot
import com.example.arslanka.weblab4.repositories.ShotRepository
import org.springframework.stereotype.Service

@Service
class ShotService(
    private val shotRepository: ShotRepository,
) {
    fun finShotByOwnerId(id: Int): Shot? {
        return shotRepository.findByOwnerId(id)
    }

    fun finShotsByOwnerId(id: Int): List<Shot> {
        return shotRepository.findAllByOwnerId(id)
    }

    fun insertShot(shot: Shot): Shot {
        return shotRepository.insert(shot)
    }

    fun deleteShotsByOwnerId(id: Int): List<Shot> {
        return shotRepository.deleteAllByOwnerId(id = id)
    }
}