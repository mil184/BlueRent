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
      comments: [],
      errorMessages: {
        companyName: " ",
        userId: " ",
        grade: " ",
        text: " ",
      },
    };
  },

  created() {
    axios.get('rest/rentACars/getAll')
      .then(response => {
        this.companies = response.data;
      })
      .catch(error => console.error(error));
      
      axios.get('rest/comments/getAll')
      .then(response => {
        this.comments = response.data;
      })
      .catch(error => console.error(error));
  },

  template: `
    <div class="create-container">
      <h2>CREATE COMMENT</h2>
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
        
        <p>comment number1: </p>
  
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
        this.errorMessages.grade = "Please enter grade.";
      }

      if (!this.comment.text) {
        valid = false;
        this.errorMessages.text = "Please enter text.";
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
			console.log(response.data);
			
			axios.put('rest/rentACars/updateGrade/' + this.comment.companyName).catch(error => console.error(error));
        })
        .catch(error => console.error(error));
      }
    }
  }
});
