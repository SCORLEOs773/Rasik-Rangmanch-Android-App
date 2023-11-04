package com.example.rasikrangmanch

interface Destinations
{
    val route:String
}

object Login: Destinations
{
    override val route = "Login"
}

object Home: Destinations
{
    override val route = "Home"
}

object Menu: Destinations
{
    override val route = "Menu"
}

object Payment: Destinations
{
    override val route = "Payment"
}