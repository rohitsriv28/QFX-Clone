package com.example.qfxclone.ui.components


fun isAlphabet(testString: String) = testString.matches(Regex("^[A-Za-z]$"))
fun isInt(testInt: String) = testInt.matches(Regex("^[a-zA-Z0-9]+$"))

//<--LoginPage-->
fun isStrongPassword(password: String): Boolean {
    val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$")
    return passwordRegex.matches(password)
}


//<--Registration Form-->
fun isValidFirstName(name: String): Boolean {
    return name.isNotEmpty() && name.length >= 3 && !name.contains(Regex("\\d"))
}

fun isValidMiddleName(name: String): Boolean {
    return name.isNotEmpty() && name.length >= 3 && !name.contains(Regex("\\d"))
}

fun isValidLastName(name: String): Boolean {
    return name.isNotEmpty() && name.length >= 3 && !name.contains(Regex("\\d"))
}

fun isValidPassword(password: String): Boolean {
    return password.matches(Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}\$"))
}


fun isValidEmail(email: String): Boolean {
    return email.matches(Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$"))
}









