Vue.component("create-comment", {
  data: function () {
    return {
      comment: {
        companyName: null,
        userId: null,
        grade: null,
        text: null,
      },
      companies: [], // Array to store the list of companies
      errorMessages: {
        companyName: "Please select a company",
        userId: "Please enter id",
        grade: "Please enter grade",
        text: "Please enter text",
      },
    };
  },

  created() {
    axios.get('rest/rentACars/getAll')
      .then(response => {
        this.companies = response.data;
      })
      .catch(error => console.error(error));
  },

  template: `
    <div class="create-container">
      <h2>CREATE COMMENT2</h2>
      <form class="login-form" @submit.prevent="tryCreate">
      
        <p>Enter grade:</p>
        <small>{{ errorMessages.grade }}</small>
        <input type="text" placeholder="Grade" v-model="comment.grade">
        
        <p>Enter text:</p>
        <small>{{ errorMessages.text }}</small>
        <input type="text" placeholder="Text" v-model="comment.text">
        
        <p>Select company:</p>
        <small>{{ errorMessages.companyId }}</small>
        <select v-model="comment.companyName">
          <option v-for="company in companies">{{ company.name }}</option>
        </select>
  
        <input type="submit" value="Create">
      </form>
    </div>
  `,

  methods: {
    tryCreate() {
      let valid = true;
      this.errorMessages = {};

      if (!this.comment.grade) {
        valid = false;
      }

      if (!this.comment.text) {
        valid = false;
      }

      if (!this.comment.companyName) {
        valid = false;
      }

      if (valid) {
        axios.post('rest/comments/create', {
          "companyName": this.comment.companyName,
          "userId": 1,
          "grade": this.comment.grade,
          "text": this.comment.text
        })
        .then(response => {
          // Handle success if needed
          console.log(response.data);
        })
        .catch(error => console.error(error));
      }
    }
  }
});
