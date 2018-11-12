<template>
  <div class="service">
    <h1>{{ msg }}</h1>
    <h2>REST service call results</h2>

    <button @click="callRestService()">CALL Spring Boot REST backend service</button>

    <h4>Backend response: {{ response }}</h4>

    <h4>Taskboard:</h4>
    {{ taskboard }}
    <table>
      <tr>
        <th>To Do</th>
        <th>Planned</th>
        <th>Selected</th>
        <th>Done</th>
      </tr>
      <tr>
        <td>
          <ul>
            <li v-for="item in taskboard.data.tasksTodo">
              <span>{{ item.title }}</span>
              <a href="">Edit</a>
              <a href="">Delete</a>
            </li>
          </ul>
        </td>
        <td>
...
        </td>
        <td>
...
        </td>
        <td>
...
        </td>
      </tr>
    </table>

  </div>
</template>

<script>
// import axios from 'axios'
import {AXIOS} from './http-common'

export default {
  name: 'service',
  data () {
    return {
      msg: 'HowTo call REST-Services:',
      taskboard: null,
      response: [],
      errors: []
    }
  },
  methods: {
    // Fetches posts when the component is created.
    callRestService () {
      AXIOS.get(`/tasks`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data
          console.log(response.data)
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  },
  mounted () {
    AXIOS
      .get('tasks')
      .then(response => (this.taskboard = response))
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
