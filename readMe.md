Setup and Prerequisites:

GIT
Gradle
RedisDB/Server
Java7+

To clone this project you need to run  "git clone git@github.com:sudheerjay/TreeOperations.git"


This is a small microservice that provides APIs to getDescendants of a given node and to change a parent of any given node.
this application run on spring boot's tomcat server port 8090.

this application works with static data for now.


This is the below sample tree for which this code is written for
				  1
				/   \
			   /     \
			  2       5
			 /  \    / \
			3	4   6   7
				   / \
				  8   9

Redis DB is used to persist all the info gathered from the above mentioned APIs.

Redis Keys:

node information is stored under the key "node_{id}"
node descendants information is stored under the key "node_{id}_descendants"

1) you need to install gradle to run this application. once you have gradle setup,
   goto the cloned location and run 
	-> "./gradle wrapper"
	-> "./gradlew clean build".
	-> once the application builds successfully, you can run it by using "./gradlew bootRun"
   	   this will start the application and you can call the APIs.
  
   
2) you need to install Redis on your server/machine to run this code
	-> For mac users you need to run "brew install redis" to install redis
	-> To start the redis DB, you can run brew services start redis.



Available APIs:

1) getDescendants of a given node: http://localhost:8090/tree/node/getDescendants?nodeId={id}
2) changeParent of a given node: http://localhost:8090/tree/node/changeParent?nodeId={id}&parentId={id}


NOTE: change parent API is dependent on the getDescendants API. this is because we need all the descendants to be rearranged after the node's parent is changed.

for any queries reach out to me @ sudheerjay@gmail.com


				  