from flask import Flask, render_template
from flask_socketio import SocketIO, emit

app = Flask(__name__,template_folder='templates',static_folder='static')
app.config['SECRET_KEY'] = ''
socketio = SocketIO(app)

@app.route('/')
def index():
    return render_template('index.html')

@socketio.on('connect')
def test_connect():
    print('Client connected')
    emit('my response', {'data': 'Connected'})

@socketio.on('disconnect')
def test_disconnect():
    print('Client disconnected')

@socketio.on('my event')
def handle_my_custom_event(json):
    print('received json: ' + str(json))

if __name__ == '__main__':
    socketio.run(app,host='0.0.0.0',debug=True)
