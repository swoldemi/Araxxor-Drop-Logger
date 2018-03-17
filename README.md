[![Build Status](https://travis-ci.com/swoldemi/Araxxor-Drop-Logger.svg?token=7krzK5VdKs3se3MYzZF9&branch=master)](https://travis-ci.com/swoldemi/Araxxor-Drop-Logger) 
# Araxxor Drop Logger

## Why?
1) I got tired of looking at [this](https://docs.google.com/spreadsheets/d/1ML9JzX3Pf_XLlslYFOtpgXIUedVf4UCY_l0xi25gifE/edit#gid=0) spreadsheet.
2) Java is cool.
3) Making this was more fun than actually farming Araxxor.
4) I have enough Araxxor kills
## Requirements
 - [MySQL 5.7](https://dev.mysql.com/downloads/mysql/)
 - [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 
## Usage
 0) Make sure to have the requirements downloaded and installed
 1) Clone this repo </br>
 ```
 git clone http://github.com/swoldemi/Araxxor-Drop-Logger
 ```
 2) Change your working directory to ./bin/ </br>
 ```
 cd bin/
 ```
 3) Run the .jar file </br>
 ```
 java -cp AraxxorDropLogger-0.0.1.jar me.swoldemi.araxxorDropLogger.Main
 ```
 4) Note it's required that lib/ is within bin/ for the .jar to know where to look for the MySQL JavaDB Connector.
 5) This can be changed by updating [this](https://github.com/swoldemi/Araxxor-Drop-Logger/blob/24c2cc02d9994ea944ae0c9e89ca0472390a6f58/pom.xml#L74) and [this](https://github.com/swoldemi/Araxxor-Drop-Logger/blob/24c2cc02d9994ea944ae0c9e89ca0472390a6f58/pom.xml#L60) and building the project with [Apache Maven](https://maven.apache.org/download.cgi) while in the root of the directory.
 ```
 mvn clean package
 ```
 
# Screenshots
<div style="text-align:center"><img src ="https://github.com/swoldemi/Araxxor-Drop-Logger/blob/master/docs/login_screenshot.PNG" /></div>
<div style="text-align:center"><img src ="https://github.com/swoldemi/Araxxor-Drop-Logger/blob/master/docs/main_menu_screenshot.PNG" /></div>
<div style="text-align:center"><img src ="https://github.com/swoldemi/Araxxor-Drop-Logger/blob/master/docs/pets.PNG" /></div> </br>
<div style="text-align:center"><img src ="https://github.com/swoldemi/Araxxor-Drop-Logger/blob/master/docs/log_drop_interface.gif"/></div>

## To do
- [ ] LootStatistics class
- [ ] 100% test coverage
- [ ] Online SQL Database
- [ ] Well defined use cases
- [ ] Better UX/UI
- [ ] Find a JTable alternative
- [ ] Code cleanup
- [ ] Automated logging?
