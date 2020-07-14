# Elliot's FootballManager 
The aim of this project is to produce a Console based application that emulates the popular Football Manager series.

## How to run
1. Clone project and CD into the top level directory
2. Generate the jar by running:
```java
mvn clean package
```
3. Copy the database file into the football manager directory
```bash
cp FootballManagerDatabase ./footballManager/
```
4. To start the game, run the following command:
```java
java -jar elliots-football-manager.jar
```

## Tests
To run the test suite (_If there are any tests_) Simply run:
```java
mvn clean test
```

## License 
MIT License

Copyright (c) 2018, Elliot Ball

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
