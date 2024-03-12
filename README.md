# inventoryService
This project is for basic understanding of CRUD operations without an actual DB connections. <br>
I used Java 8 features to do the CRUD operations in this project.

<h3>Prerequisites </h3>
How I declared items in inventory by default?
* I pre-defined 3 items already in Static Block for a static variable
* Used Static because of the memory utilization, it will share the variable instead of creating new object everytime.
* Static block will load first before Class load its properties.
* So, when application starts by default I have 3 items in the Inventory to do some CRUD operations.
* Refer <i>"inventoryServiceImpl.java"</i> file for the Static block implementation.

Why I choose SET Collection for storing inventory items?
* I was chosen SET Collection instead of LIST because of avoiding duplicates in Inventory.
* SET will allow duplicates if we have  <i>Set<Object\></i> instead of any primitive wrapper class.
* To avoid duplicates of Object like inventory or employee, we have to override the <i>hashCode and equals</i> method in the Object class.
* Refer <i>"Inventory.java"</i> for the overriding of hashCode and equals method for <i>itemId and itemName</i> fields alone because those are unique when it comes to inventory items.

How I added default contextPath of <i>"/inventoryservice/v1"</i>?
* I added a property in application.properties file to achieve this.
  * <i>server.servlet.context-path = /inventoryservice/v1</i>

<h3>POST / CREATE</h3>
* Created POST http method to add/save an item in the Inventory.
* In SET predefined <i>add()</i> method returns boolean using that created if else statements.
    * True: returns the saved object.
    * False: returns the empty Inventory object.

<h3>GET / RETRIEVE</h3>
* Created GET http method to retrieve all the items in the Inventory.
* Because of the Static block and variable, by default I have 3 items in Inventory to return.
* It will return all the items in the Inventory.

<h3>PUT / UPDATE</h3>
* Created PUT http method to update an item in the inventory.
* Used the pathVariable to pass an itemId to fetch and update the item in inventory.
* Used Java 8 stream feature for the inventorySet, added filter operation to get the item to be updated and used map method to that item to update the item in inventory.
* Used <i>findFirst()</i> to get the element from Stream and used <i>get()</i> method to return as Inventory object instead of <i>Optional<Inventory\></i>.

<h3>DELETE</h3>
* Created DELETE http method to delete an item in the inventory.
* Used the requestParam to get an itemId to delete that respective item in the inventory.
* Used same process of Java 8 in PUT operation to get a unique item from the inventory and stored in a variable.
* Passed that variable in pre-defined <i>remove()</i> method of SET to delete/remove an item from the inventory.

<h3>DELETE ALL</h3>
* Created DELETE http method to delete all the items in the inventory.
* Which will empty the inventorySet.
* invoked the pre-defined <i>removeAll()</i> method of SET and passed the inventorySet as argument to empty the inventorySet.

<h3>LOGGING</h3>
* Used SLF4j for logging.
* In controller file used traditional way by importing SLF4j package and used the logger.
* In serviceImpl file used lombok <i>@Slf4j</i> annotation to do the logging, default variable is <i>log</i>.
* In application.properties file added some properties related to the logging like pattern, rollover policy, filename, etc.
* For log file reference check <i>"src/main/java/resources/logs/inventoryService.log"</i> file.
* For more details on the logging using xml configuration and application.properties, also for pattern details check the links below,
  * <a href="https://www.codejava.net/frameworks/spring-boot/logback-rolling-files-example#:~:text=Basically%2C%20you%20just%20need%20to%20specify%20the%20following,enable%20daily%20rolling%20files%20logging%3A%20logging.file.name%3DMyApp.log%20logging.pattern.rolling-file-name%3DMyApp-%25d%20%7Byyyy-MM-dd%7D.%25i.log"> code Java for application.properties logging </a>
  * <a href="https://dzone.com/articles/configuring-logback-with-spring-boot"> Dzone for xml configuration logging and pattern details </a>