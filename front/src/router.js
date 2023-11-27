import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {
        path: "/",
        name: "Products",
        component: () => import("./components/Products"),
    },
    {
        path: "/product/:id",
        name: "Product",
        component: () => import("./components/Product"),
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;