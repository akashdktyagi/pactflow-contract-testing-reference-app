
<script>

import axios from 'axios';

export default {
  props: {
    msg: String
  },
  data(){
    return {
      apiURL: import.meta.env.VITE_EMPLOYEE_API_URL,
      employeeInfo: '',
      errorMessage: '',
      id: '',
      empId: '',
      name: '',
      age: '',
      email:'',
      phone: '',
      department: '',
      salary: '',
      designation: ''
    }
  },
  methods: {
    getListOfAllEmployee(){
      console.log("API URL: " + this.apiURL);
      axios
      .get(this.apiURL + "/employee") 
      .then(
        res=>{
          this.employeeInfo = res.data;
          console.log("Status for get Request: " + res.status);
        }
      ).catch((error)=> {
          if (error.response) {
            // Request made and server responded
              console.log("Error Response Data:" + JSON.stringify(error.response.data));
              console.log("Error Response status:" + error.response.status);
              console.log("Error Response headers:" + JSON.stringify(error.response.headers));
              this.errorMessage = "Data:" + JSON.stringify(error.response.data) + " Status: " + error.response.status + " Headers: " + JSON.stringify(error.response.headers);

          } else if (error.request) {
            // The request was made but no response was received
            console.log(error.request);
            this.errorMessage = error.message;
            // this.errorMessage = error.message;
          } else {
            // Something happened in setting up the request that triggered an Error
            console.log('Error', error.message);
            this.errorMessage = error.message;
          }

          });
    }
  },
  components:{

  },
  emits: [],
  computed: {

  }
}

</script>

<template>
  <div class="greetings">
    <h1 class="green">List of All Employees</h1>
    <button class="button button2" @click="getListOfAllEmployee()">Get List of Employee</button>
  </div>

  <div>
    <table v-if="employeeInfo">
      <tr>
        <th>id</th>
        <th>empId</th>
        <th>name</th>
        <th>age</th>
        <th>email</th>
        <th>phone</th>
        <th>department</th>
        <th>salary</th>
        <th>designation</th>
      </tr>
      <tr v-for="emp in employeeInfo">
          <td>{{emp.id}}</td>
          <td>{{emp.empId}}</td>
          <td>{{emp.name}}</td>
          <td>{{emp.age}}</td>
          <td>{{emp.email}}</td>
          <td>{{emp.phone}}</td>
          <td>{{emp.department}}</td>       
          <td>{{emp.salary}}</td>       
          <td>{{emp.designation}}</td>                                          
      </tr>
    </table>
  </div>
    <hr>
    <h2>API response: </h2>
    <div> {{employeeInfo}}</div>
    <hr>
    <div>errors : {{errorMessage}}</div>

</template>

<style scoped>
h1 {
  font-weight: 500;
  font-size: 2.6rem;
  top: -10px;
}

h3 {
  font-size: 1.2rem;
}

div {
  text-align: center;
}

.greetings h3 {
  text-align: center;
}

.button {
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}

.button1 {background-color: #4CAF50;} /* Green */
.button2 {background-color: #008CBA;} /* Blue */

table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

/* .greetings h1,


@media (min-width: 1024px) {
  .greetings h1,
  .greetings h3 {
    text-align: left;
  } 
}*/
</style>
