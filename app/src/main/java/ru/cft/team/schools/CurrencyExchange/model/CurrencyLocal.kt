package ru.cft.team.schools.CurrencyExchange.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyLocal(
    val ID: String?,        // "ID": "R01010",
    val NumCode: String?,   // "NumCode": "036",
    val CharCode: String?,  // "CharCode": "AUD",
    val Nominal: Int?,      // "Nominal": 1,
    val Name: String?,      // "Name": "Австралийский доллар",
    val Value: Float?,      // "Value": 78.3702,
    val Previous: Float?    // "Previous": 80.3676
): Parcelable
