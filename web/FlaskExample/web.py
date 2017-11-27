from flask import Flask, render_template, redirect, jsonify, url_for, request, session
from flask_restful import Api
from flask_wtf import Form
from flask_wtf.csrf import CsrfProtect
from wtforms import SelectField
import db.helper as connection


# initalize server
# app = Flask(__name__, template_folder='web/pages', static_folder='web/vendor')
app = Flask(__name__, template_folder='views')
api = Api(app)
app.config['SECRET_KEY'] = 'A0Zr98j/3yX R~XHH!jmN]LWX/,?RT'
CsrfProtect(app)

# create connection object and get data for teams and players
db = connection.Connection()


@app.route('/', methods=['GET', 'POST'])
def index():
    "Defines method that returns the correct html file for the home page"
    testing = "12ddef2"
    return render_template("index.html", test_var=testing)

@app.route('/lt', methods=['GET', 'POST'])
def lt():
    "Defines method that returns latest tweet view"
    tweet = db.get_latestTweet()[0]
    return render_template("latest_tweet.html", text_id=tweet[0], user_id=tweet[1], msg=tweet[2], date=tweet[3], source=tweet[4], loc=tweet[5])

@app.route('/count', methods=['GET', 'POST'])
def count():
    "Defines method that returns total number of tweets in the database"
    count = db.get_totalTweets()[0]
    return render_template("count_tweets.html", count=count[0])

# @app.route('/get_filter', methods=['POST'])
# def get_filter():
#     filter_form = request.form['filter']
#     text_id = 933291745722351617
#     tweets = db.get_tweets(text_id)
#     return render_template("tables.html",form=filter_form)

# @app.route('/', methods=['GET', 'POST'])
# def index():
#     class SelectTeamForm(Form):
#         teams = db.get_teams()
#         name = SelectField(coerce=int, choices=teams, default=1610612737L)
#     form = SelectTeamForm()
#     print(form.errors)
#     # handle post request in form
#     if form.validate_on_submit():
#         session['TEAM_ID'] = form.name.data
#         return redirect('/player')
#     return render_template("index.html",form=form)

# @app.route('/player', methods=['GET','POST'])
# def player():
#     class SelectPlayerForm(Form):
#         team_id = session['TEAM_ID']
#         players = db.get_players(team_id)
#         name = SelectField(coerce=int, choices=players)
#     form = SelectPlayerForm()
#     # handle post request in form
#     if form.validate_on_submit():
#         session['PLAYER_ID'] = form.name.data
#         return redirect('/stats')
#     return render_template("player.html", form=form)

# @app.route('/stats', methods=['POST', 'GET'])
# def stats():
#     player_id = session['PLAYER_ID']
#     stats = db.get_stats(player_id)[0]
#     return render_template("stats.html", name=stats[1], blocks=stats[9], drfgm=stats[11], drfga=stats[12], drfgpct=stats[13])

# create simple api that takes in id and response with stats of said player
# ex http://localhost:5000/api/201960
@app.route('/test/<id>', methods=['GET','POST'])
def test(id):
    "Simple page that returns raw api data"
    text_id = id
    tweets = db.get_tweets(text_id)
    return jsonify(tweets)

if __name__ == '__main__':
    app.run(debug=True, host='localhost')
