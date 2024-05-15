package com.example.moneymanager20logic.Database.presetation

import com.example.moneymanager20logic.Database.Daos.Saving
import com.example.moneymanager20logic.Database.table.Entities

sealed interface MoneyEvent {

    data class Delete(val saving: Entities):MoneyEvent

    data class Save(
        var title:String,
        var amount:String,
    ):MoneyEvent

    data class Update(
        //var title: String,
        var amount: Double,
        var title: String
    ) :MoneyEvent

    data class Sum(
      var sum:Double
    ):MoneyEvent
}
