import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import OktaVue from '@okta/okta-vue'
import { oktaAuth } from './okta';
import "@/assets/tailwind.css";
import "./assets/tailwind.css";
import './assets/tailwind.css'

createApp(App)
    .use(router)
    .use(OktaVue, {
        oktaAuth,
         onAuthResume: () => {
            router.push('/login')
        }
    })
    .mount('#app')