# UOM_ShadowLife
This is a graphical simulation developed as an assestment for SWEN20003 at University of Melbourne. The simulation is a world inhabited by creatures called gatherers. Their purpose in life is to gather fruit from trees, and deposit them at stockpiles. Once they have gathered all the fruits from the trees, they rest in front of fences. However, to make their life difficult, there is the thief who aims to steal fruit from their stockpiles and place it in their hoards. The thief and gatherers follow rigid rules, and the simulation will halt once they all reach their final goals (the fence).

The behaviour of the simulation is determined by the 'world file' loaded when the Shadow Life starts: each gatherer, thief,  and  other  element  begins  at  a  specified  location  and follows a set of rules to determine their behaviour.  Once all gatherers and thieves have reached a fence, the simulation halts, and the amount of fruit at each stockpile and hoard is tallied up. The simulation proceeds in ticks, with the tick rate (time between ticks) determined by a command-line  parameter. If more than a maximum number of ticks (also determined by a command-lineparameter) pass before halting, the simulationtimes out. Otherwise, the number of elapsed ticks, together with the amounts of fruit at each location, is printed to form the result of the world file.

The image below shows a screenshot of a system in progress.

![simulation](https://user-images.githubusercontent.com/62633701/113553613-649a4200-963b-11eb-8b68-d8f86fe1281b.png)
