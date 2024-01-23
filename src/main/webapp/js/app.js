const LogIn = {template: "<log-in></log-in>"}
const Register = {template: "<register></register>"}
const UserPage = {template: "<userPage></userPage>"}
const EditUser = {template: "<editUser></editUser>"}
const CreateRentACar = { template : "<create-rentACar></create-rentACar>" }
const HomePage = { template : "<home></home>" }
const RentACarPage = { template : "<rentACarPage></rentACarPage>"}

const router = new VueRouter({
	mode: 'hash',  
	routes: [
		{path : "/login", component: LogIn},
		{path : "/register", component: Register},
		{ path : "/home", component : HomePage },
		{ path : "/rentACars/create", component: CreateRentACar},
		{ path : "/rentACars/:name", component: RentACarPage},
        {path : "/:username", component: UserPage},
        {path : "/:username/edit", component: EditUser}
        
        ]
	
});

var app = new Vue({
	router,
	el: "#mainPage"
});