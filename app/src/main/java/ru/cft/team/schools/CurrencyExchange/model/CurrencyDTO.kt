package ru.cft.team.schools.CurrencyExchange.model

data class CurrencyDTO(
    val Date: String?, // "Date": "2022-03-16T11:30:00+03:00",
    val PreviousDate: String?, // "PreviousDate": "2022-03-15T11:30:00+03:00",
    val PreviousURL: String?, //"PreviousURL": "\/\/www.cbr-xml-daily.ru\/archive\/2022\/03\/15\/daily_json.js",
    val Timestamp: String?, // "Timestamp": "2022-03-15T23:00:00+03:00",
    val Valute: HashMap<String, DetailCurrencyDTO> // "Valute": {
)

data class DetailCurrencyDTO(
    val ID: String?,        // "ID": "R01010",
    val NumCode: String?,   // "NumCode": "036",
    val CharCode: String?,  // "CharCode": "AUD",
    val Nominal: Int?,      // "Nominal": 1,
    val Name: String?,      // "Name": "Австралийский доллар",
    val Value: Float?,      // "Value": 78.3702,
    val Previous: Float?    // "Previous": 80.3676
)

