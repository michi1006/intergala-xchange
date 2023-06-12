# intergala-xchange
This program provides a money exchange from intergalactic currencies to so called 'Credits' using Roman literals.

## Program
The intergala-xchange  programm can be run simplified by running the **IntergalaXchange_Main** class. This class reads default input.txt from resources folder and outputs the conversion result to the console.

Furthermore, there is the *JavaFX* program **IntergalaXchange**, which offers a more user-friendly, interactive UI. There the input can be read from a .txt-file and modified manually in the input text area.

## Classes

General:

1) IntputInterpreter - the BI class to handle the input conversion which returns the output and conversion feedback
2) RomanNumber - the enum class holding valid roman number literals and the information, if they can be repeated
3) RomanNumberConvertor - the BI class doing the roman number conversion logic

JavaFX:
1) IntergalaXchange - the application class to run the program
2) IntergalaXchangeController - the class holding the actual logic to retrieve and write data to the UI, button 3) implementations

Main:
1) IntergalaXchange_Main - simple application class to run the default input.txt conversion

## Tests

**RomanNumberTest.java**
To test simple roman-to-arabic-number conversion.
- MMXXIII = 2023
- MXL = 1040
- MCMXLIV = 1944
- XXXX = Invalid
- MMIM = Invalid
- DDIX = Invalid

**InputInterpreterTest.java**
To test simple input reading and input interpreting functionality. 
- reading from valid file
- reading from invalid file
- result according to test output
- ensure NPE safety
