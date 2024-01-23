const LogIn = {template: "<log-in></log-in>"}
const Register = {template: "<register></register>"}
const UserPage = {template: "<userPage></userPage>"}
const EditUser = {template: "<editUser></editUser>"}
const AdminPage = {template: "<adminPage></adminPage>"}
const RegisteredUsers = {template: "<registeredUsers></registeredUsers>"}
const ManagerPage = {template: "<managerPage></managerPage>"}
const CustomerPage = {template: "<customerPage></customerPage>"}

const router = new VueRouter({
	mode: 'hash',  
	routes: [
		{path : "/login", component: LogIn},
		{path : "/register", component: Register},
		{path : "/registeredUsers", component: RegisteredUsers},
        {path : "/:username", component: UserPage},
        {path : "/:username/edit", component: EditUser},
        {path : "/:username/adminPage", component: AdminPage},
        {path : "/:username/managerPage", component: ManagerPage},
        {path : "/:username/customerPage", component: CustomerPage}
        ]
	
});

var app = new Vue({
	router,
	el: "#mainPage"
});