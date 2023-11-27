import axios from 'axios'

// const PRODUCT_API_URL = 'http://localhost:8080/api/v1'
const PRODUCT_API_URL = 'http://192.168.0.103:8080/api/v1'

class ProductDataService {

    retrieveAllProducts() {
        return axios.get(`${PRODUCT_API_URL}/products`);
    }

    retrieveProduct(id) {
        return axios.get(`${PRODUCT_API_URL}/products/${id}`);
    }

    deleteProduct(id) {
        return axios.delete(`${PRODUCT_API_URL}/products/${id}`);
    }

    updateProduct(id, product) {
        return axios.put(`${PRODUCT_API_URL}/products/${id}`, product);
    }

    createProduct(product) {
        return axios.post(`${PRODUCT_API_URL}/products`, product);
    }
}

export default new ProductDataService()