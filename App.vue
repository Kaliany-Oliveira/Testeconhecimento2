<template>
  <div id="app">
    <h1>Lista de Tarefas</h1>
    <!-- Input para adicionar nova tarefa -->
    <input
      v-model="newTodo"
      placeholder="Adicione uma nova tarefa"
      @keyup.enter="addTodo"
    />
    <!-- Lista de tarefas -->
    <ul>
      <li v-for="todo in todos" :key="todo.id">
        <!-- Checkbox para marcar como concluído -->
        <input
          type="checkbox"
          v-model="todo.completed"
          @change="updateTodo(todo)"
        />
        <!-- Título da tarefa -->
        <span :class="{ completed: todo.completed }">{{ todo.title }}</span>
        <!-- Botão para excluir a tarefa -->
        <button @click="deleteTodo(todo.id)">Excluir</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "App",
  data() {
    return {
      todos: [], // Lista de tarefas
      newTodo: "" // Texto da nova tarefa
    };
  },
  methods: {
    // Método para buscar todas as tarefas
    fetchTodos() {
      axios
        .get("http://localhost:8080/api/todos")
        .then(response => {
          this.todos = response.data; // Atualiza a lista de tarefas
        })
        .catch(error => console.error(error));
    },
    // Método para adicionar uma nova tarefa
    addTodo() {
      if (this.newTodo.trim() === "") return; // Ignora se o campo estiver vazio
      const todo = { title: this.newTodo, completed: false }; // Cria um novo objeto de tarefa
      axios
        .post("http://localhost:8080/api/todos", todo) // Envia a nova tarefa para o backend
        .then(response => {
          this.todos.push(response.data); // Adiciona a tarefa à lista
          this.newTodo = ""; // Limpa o campo de input
        })
        .catch(error => console.error(error));
    },
    updateTodo(todo) {
      axios.put('http://localhost:8080/api/todos/${todo.id}', todo, {
        headers: { "Content-Type": "application/json" }
      })
      .then(response => console.log("Tarefa atualizada:", response.data))
      .catch(error => console.error("Erro ao atualizar tarefa:", error));
    },
    // Método para excluir uma tarefa
    deleteTodo(id) {
      axios
        .delete("http://localhost:8080/api/todos/${id}")
        .then(() => {
          this.todos = this.todos.filter(todo => todo.id !== id);
        })
        .catch(error => console.error("Erro ao excluir tarefa:", error));
    }
  },
  mounted() {
    // Quando o componente é carregado, busca as tarefas
    this.fetchTodos();
  }
};
</script>

<style>
/* Estilo para tarefas concluídas */
.completed {
  text-decoration: line-through;
}
</style>