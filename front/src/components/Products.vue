<template>
  <div class="container">
    <h3>All Products</h3>
    <div v-if="message" class="alert alert-success">{{ this.message }}</div>
    <div class="container">
      <table class="table">
        <thead>
        <tr>

          <th>First Name</th>
          <th>Last Name</th>
          <th>Email Id</th>
          <th>Update</th>
          <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="product in products" v-bind:key="product.id">

          <td>{{ product.firstName }}</td>
          <td>{{ product.lastName }}</td>
          <td>{{ product.emailId }}</td>
          <td>
            <button class="btn btn-warning" v-on:click="updateProduct(product.id)">
              Update
            </button>
          </td>
          <td>
            <button class="btn btn-danger" v-on:click="deleteProduct(product.id)">
              Delete
            </button>
          </td>
        </tr>
        </tbody>
      </table>
      <div class="row">
        <button class="btn btn-success" v-on:click="addProduct()">Add</button>
      </div>
    </div>
  </div>
</template>
<script>
import ProductDataService from "../service/ProductDataService";

export default {
  name: "Products",
  data() {
    return {
      products: [],
      message: "",
    };
  },
  methods: {
    refreshProducts() {
      ProductDataService.retrieveAllProducts().then((res) => {
        this.products = res.data;
      });
    },
    addProduct() {
      this.$router.push(`/product/-1`);
    },
    updateProduct(id) {
      this.$router.push(`/product/${id}`);
    },
    deleteProduct(id) {
      ProductDataService.deleteProduct(id).then(() => {
        this.refreshProducts();
      });
    },
  },
  created() {
    this.refreshProducts();
  },
};
</script>