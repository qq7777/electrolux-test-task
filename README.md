# Ovens API

Test task for Electrolux.
API that allows to manage ovens.

**Save Oven**
----
 _Creates a new oven and saves it into database_

* **URL**

  _/ovens_

* **Method:**
  
  `POST`
  
* **Request Data**

  _{
	"running": true,
	"temp": 11,
	"mode": "GRILL"
 }_

* **Sample Call:**

 _curl -XPOST -H "Content-type: application/json" -d '{"running":true,"temp":11,"mode":"GRILL"}' 'http://localhost:8088/ovens'_ 

* **Response:**
  
  _Same object with filled id_

  * **Code:** 200 <br />
    **Content:** `{"id":"5a7b2781010bc21ad8381f90","running":true,"temp":11,"mode":"GRILL"}`
 
 **Get all ovens**
----
 _Finds all ovens in database and returns them_

* **URL**

  _/ovens_

* **Method:**
  
  `GET`

* **Sample Call:**

 _curl -XGET -H "Content-type: application/json" 'http://localhost:8088/ovens'_ 

* **Response:**
  
  * **Code:** 200 <br />
    **Content:** `[
    {
        "id": "5a7b2781010bc21ad8381f90",
        "running": true,
        "temp": 11,
        "mode": "GRILL"
    },
    {
        "id": "5a7b27e7010bc21ad8381f91",
        "running": true,
        "temp": 11,
        "mode": "GRILL"
    }]`
 
 **Update parameters for an oven**
----
 _Finds oven by id and updates parameters_

* **URL**

  _/ovens/update_

* **Method:**
  
  `POST`
  
* **Request Data**

  _{
  "id": "5a7b2781010bc21ad8381f90",
	"running": true,
	"temp": 11,
	"mode": "GRILL"
 }_

* **Sample Call:**

 _curl -XPOST -H "Content-type: application/json" -d '{
        "id": "5a7b2781010bc21ad8381f90",
        "running": true,
        "temp": 11,
        "mode": "GRILL"
    }' 'http://localhost:8088/ovens/update'_ 

* **Response:**

  * **Code:** 200 <br />
    **Content:** `{
        "id": "5a7b2781010bc21ad8381f90",
        "running": true,
        "temp": 11,
        "mode": "GRILL"
    }`

    
**Turn on/off oven**
----
 _Turns on/off an existing oven_

* **URL**

  _ovens/turnOn/{id}_
  
  _ovens/turnOff/{id}_

* **Method:**
  
  `GET`
   
* **Sample Call:**

 _curl -XGET -H "Content-type: application/json" 'http://localhost:8088/ovens/turnOn/5a7b2781010bc21ad8381f90'_  
 
* **Response:**

  * **Code:** 200 <br />
    **Content:** `{
    "id": "5a7b2781010bc21ad8381f90",
    "running": true,
    "temp": 11,
    "mode": "GRILL"
}`

**Delete oven**
----
 _Removes an existing oven from database_

* **URL**

  _ovens/delete/{id}_
 
* **Method:**
  
  `GET`
   
* **Sample Call:**

 _curl -XGET -H "Content-type: application/json" 'http://localhost:8088/ovens/delete/5a7b2781010bc21ad8381f90'_  
 
* **Response:**

  * **Code:** 200 <br />

 
