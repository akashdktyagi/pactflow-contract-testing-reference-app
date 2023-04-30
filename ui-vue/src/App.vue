<script setup>
import { RouterLink, RouterView } from 'vue-router'

</script>

<template>

<div id="app">
  <h1>Employee Management App</h1>
  <h4>Purpose: Contract Testing Demonstration</h4>
  <p><b>Author:</b> Akash Tyagi. Reach out at: akashdktyagi@gmail.com</p>
  <menu>
    <nav>
        <ul>
            <li><RouterLink to="/">Home</RouterLink></li>
            <li><span>||</span></li>
            <li><RouterLink to="/employee">Get Employee</RouterLink></li>
            <!--
            <li><RouterLink to="/createappointments">Create Appointments</RouterLink></li>
            <li><span>||</span></li>
            <li><RouterLink to="/doctors">Get Doctors</RouterLink></li>
            <li><RouterLink to="/createdoctors">Create Doctors</RouterLink></li>
            <li><span>||</span></li>
            <li><RouterLink to="/patients">Get Patients</RouterLink></li>
            <li><RouterLink to="/createpatients">Create Patients</RouterLink></li>
            <li><span>||</span></li>
            -->
        </ul>
    </nav>
  </menu>
  
  <RouterView></RouterView>
</div>

</template>

<style>

h1,h4,p {
    text-align: center;
}

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: rgb(221, 19, 19);
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 50px;
  text-decoration: none;
}

li a:hover {
  background-color: #111;
}
</style>
