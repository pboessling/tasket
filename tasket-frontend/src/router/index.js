import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import TaskBoard from '@/components/TaskBoard'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'TaskBoard',
      component: TaskBoard
    }
  ]
})
