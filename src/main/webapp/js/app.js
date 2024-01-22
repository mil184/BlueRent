const LogIn = {template: "<log-in></log-in>"}
const Register = {template: "<register></register>"}
const UserPage = {template: "<userPage></userPage>"}
const EditUser = {template: "<editUser></editUser>"}
const CreateRentACar = { template : "<create-rentACar></create-rentACar>"}

const router = new VueRouter({
	mode: 'hash',  
	routes: [
		{path : "/login", component: LogIn},
		{path : "/register", component: Register},
        {path : "/:username", component: UserPage},
        {path : "/:username/edit", component: EditUser},
        {path : "/rentACars/create", component: CreateRentACar}
        ]
	
});

var app = new Vue({
	router,
	el: "#mainPage"
});