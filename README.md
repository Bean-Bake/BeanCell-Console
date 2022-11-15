# BeanCell-Console
This was the original, text-based FreeCell clone I wrote between semesters at PVCC

This project originally came about as my (incredibly) old laptop's last working USB port died. I couldn't use any mouse with it, 
only the trackpad. If you've ever tried to play any games with a trackpad, even a solitaire game, you would know just how frustrating it is.
So I decided to write a text-based version of FreeCell (my favorite type of solitaire) so I had something to play once in a while.

This was originally written using Eclipse and had pretty colors for the cards using a random extension. Since switching it over to IntelliJ 
(which doesn't have that extension), I've since updated the whitespace and dropped the colors for the time being. As well, when putting it
on GitHub, there's a possibility the linespace may be further messed up due to the Linux/Windows whitespace changes that sometimes happen.

Anyway, the way it works:

You will be prompted for what you want to do each "turn".


Valid answers are:

"select" -> then asked what column and row -> input column -> input row (starts counting from top)
"select graveyard" -> then asked which one -> input index.
"slect heaven" -> same as graveyard.

"move" -> (must have cards selected) -> then asked which column to move cards -> input column
"heaven" -> (must have one card selected) -> moves to appropriate heaven automatically
"graveyard" -> (must have one card selected) -> same as heaven
"undo" -> undoes last move (move meaning literal move, selects are not moves)
"quit" -> exits program

(All numbers start counting at 1, NOT 0.)

Thanks for viewing this incredibly silly project!

![BeanCellConsole MidGame](https://user-images.githubusercontent.com/111920505/201966017-3d0b0b90-22f2-49af-89a2-e233cbf13fc7.png)
