<template>
  <div class="register">
    <v-row class="text-center register-title animated fadeInDown">
      <v-col cols="12" xs="12" sm="12" md="12" lg="12" xl="12">
        <v-icon class="register-title-icon" color="white">mdi-grain</v-icon>
        <span class="register-title-word">Ben.</span>
      </v-col>
    </v-row>

    <v-row class="text-center animated fadeInDown" v-show="!getShowDoneView">
      <v-col cols="12" xs="12" sm="12" md="12" lg="12" xl="12">
        <p class="register-subtitle">{{ content.subtitle }}</p>
      </v-col>
    </v-row>

    <v-row class="text-center animated fadeInUp">
      <v-col cols="12" xs="12" sm="12" md="12" lg="12" xl="12" align="center">
        <register
          height="20"
          btnHeight="50"
          class="signup"
          v-if="!getShowDoneView"
          ref="register"
        ></register>
        <div v-else class="animated fadeIn">
          <v-icon size="90" color="green" class="mt-8">
            mdi-check-decagram
          </v-icon>
          <div class="done mt-3 mb-5">
            Done!
          </div>
          <v-btn outlined @click="handleRedirect" color="primary">
            返 回 首 页
          </v-btn>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { REGISTER, REQUEST } from "../common/view/Constant";
import api from "../utils/api";

export default {
  name: "Register",
  components: {
    register: () => import("../components/register/register.vue")
  },
  data: () => ({
    signup: null,
    content: REGISTER.VIEW
  }),
  methods: {
    handleRedirect() {
      window.location.replace(process.env.VUE_APP_URL);
    }
  },
  computed: {
    getShowDoneView() {
      return this.$store.getters.getRegisterDoneView;
    }
  },
  async beforeRouteEnter(to, from, next) {
    const {
      query: { token }
    } = to;
    try {
      const {
        data: { code, data }
      } = await api.register.validate(token);
      if (code === REQUEST.SUCCESS.code) {
        next(vm => {
          vm.$store.commit("updateEmail", data);
        });
      } else {
        next({ path: "signup" });
      }
    } catch (e) {
      next({ path: "signup" });
    }
  },
  beforeRouteLeave(to, from, next) {
    this.$store.dispatch("asyncUpdateRegisterDoneView", false);
    this.$refs.register.reset();
    next();
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/css/signup/signup.scss";
</style>
