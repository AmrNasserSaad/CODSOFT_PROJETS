package com.example.quiz_app.data

object SetData {


    fun getQuestion(): ArrayList<Question>{

        val que = ArrayList<Question>()

        val question1 = Question(
            1,
            "Which internet company began life as an online bookstore called 'Cabrera' ?",
            "ebay",
            "Shopify",
            "Amazon",
            "Overstock",
            3
        )
        que.add(question1)

        val question2 = Question(
            1,
            "Which of the following languages is used as a scripting language in the Unity 3D game engine?",
            "Java",
            "C#",
            "C++",
            "Objective-C",
            2
        )
        que.add(question2)

        val question3 = Question(
            1,
            "Which of these people was NOT a founder of Apple Inc?",
            "Jonathan Ive",
            "Steve Jobs",
            "Ronald Wayne",
            "Steve Wozniak",
            1
        )
        que.add(question3)

        val question4 = Question(
            1,
            "What does the term GPU stand for?",
            "Graphite Producing Unit",
            "Gaming Processor Unit",
            "Graphical Proprietary Unit",
            "Graphics Processing Unit",
            4
        )
        que.add(question4)

        val question5 = Question(
            1,
            "Moore's law originally stated that the number of transistors on a microprocessor chip would double every...",
            "Year",
            "Four Years",
            "Two Years",
            "Eight Years",
            1
        )
        que.add(question5)

        val question6 = Question(
            1,
            "What five letter word is the motto of the IBM Computer company?",
            "Click",
            "Logic",
            "Pixel",
            "Think",
            4
        )
        que.add(question6)

        val question7 = Question(
            1,
            "In programming, the ternary operator is mostly defined with what symbol(s)?",
            "??",
            "if then",
            "?:",
            "?",
            3
        )
        que.add(question7)

        val question8 = Question(
            1,
            "On which computer hardware device is the BIOS chip located?",
            "Motherboard",
            "Hard Disk Drive",
            "Central Processing Unit",
            "Graphics Processing Unit",
            1
        )
        que.add(question8)

        val question9 = Question(
            1,
            "What did the name of the Tor Anonymity Network originally stand for?",
            "The Only Router",
            "The Orange Router",
            "The Ominous Router",
            "The Onion Router",
            4
        )
        que.add(question9)

        val question10 = Question(
            1,
            "What was the first Android version specifically optimized for tablets?",
            "Eclair",
            "Honeycomb",
            "Marshmallow",
            "Froyo",
            2
        )
        que.add(question10)
        return que
    }
}