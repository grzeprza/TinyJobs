from flask import Flask, request
from flask_restful import Resource, Api
from requests import put, get

app = Flask(__name__)
api = Api(app)

users = {'m@w': 'm'}

todos = {}
jobs = []


class ChiefClass(Resource):
    def put(self, input):
        if input == 'login':
            return Login.put(self)
        if input == 'putjob':
            return PutJob.put(self, input)


class PutJob(Resource):
    def get(self, todo_id):
        return {todo_id: todos[todo_id]}

    def put(self, putjob):
        # todos[todo_id] = {'data': request.form['data'], 'user': request.form['user']}
        # print(todos)
        # return {todo_id: todos[todo_id]}
        params = {'name': request.form['name'],
                  'description': request.form['description'],
                  'address': request.form['address'],
                  'date': request.form['date'],
                  'time': request.form['time'],
                  'phone': request.form['phone'],
                  'profit': request.form['profit']}
        jobs.append(params)
        print(jobs)
        return params


class Login(Resource):
    def get(self, todo_id):
        return {todo_id: todos[todo_id]}

    def put(self):
        params = {'action': request.form['action'],
                  'email': request.form['email'],
                  'password': request.form['password']}
        print('Login / register request appeared. Parameters: ', params)

        # ****************** CASE USER TRIES TO LOG IN
        if params['action'] == 'login':
            if params['email'] in users.keys():
                return {'response': 'authorization confirmed'} if params['password'] == users[params['email']] else {'response': 'wrong password'}
            return {'response': 'no such user in database'}

        # ****************** CASE USER WANTS TO REGIStER
        if params['action'] == 'register':
            if params['email'] in users.keys():
                return {'response': 'username already exists'}
            users[params['email']] = params['password']
            print('User registered. List of users: ', users)
            return {'response': 'user ' + params['email'] + ' registered'}

api.add_resource(ChiefClass, '/<string:input>')
# api.add_resource(PutJob, '/<string:input>')
# api.add_resource(Login, '/login')




if __name__ == '__main__':
    app.run(debug=True)
