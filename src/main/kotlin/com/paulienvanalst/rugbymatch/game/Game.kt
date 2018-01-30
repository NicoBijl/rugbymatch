package com.paulienvanalst.rugbymatch.game

import org.apache.logging.log4j.LogManager
import org.springframework.context.ApplicationEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component


// deze class heeft state
@Component
class Game  {
    private val logger = LogManager.getLogger(Game::class.java)

    // start methode
    private  var score: Score = Score(Pair("A", 0), Pair("B", 0))
    private var time: Int = 0

    @EventListener
    fun kickOff(kickOff: KickOff) : String{
        val kickingTeam = kickOff.kickingTeam
        logger.error("Kick by {} is less than 10 m", kickingTeam)
        return if (kickOff.distance < 10) {
            "KickOff is less than 10 m"
        } else {
            "Let's play rugby!"
        }
    }



}

// en deze warning?
class KickOff(source: Any?, val kickingTeam: String, val distance: Int) : ApplicationEvent(source)

class KickOffCatched(source: Any?): ApplicationEvent(source)

// extension functions

class Score (private var home: Pair<String, Int>, private var visitors: Pair<String, Int>){
    override fun toString(): String {
//        return home.first + " " +  home.second + " - " + visitors.second + " " + visitors.first

        return "${home.team()} ${home.points()} - ${visitors.points()} ${visitors.team()}"
    }

    private fun Pair<String, Int>.team() = this.first
    private fun Pair<String, Int>.points() = this.second
}


