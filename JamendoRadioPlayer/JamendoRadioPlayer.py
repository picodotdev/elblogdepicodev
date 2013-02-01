#! /usr/bin/env python3

import json
import http.client
import shlex, subprocess
import signal, sys
import optparse

class JamendoRadioPlayer:

	def __init__(self):
		self.radios = None
		self.radio = None
		self.songs = None
		self.song = None

		# Capturar el Control-C para parar el reproductor
		signal.signal(signal.SIGINT, self.signalHandler)

	def signalHandler(self, signal, frame):
		sys.exit(0)

	def list(self):
		conn = http.client.HTTPConnection("api.jamendo.com")
		conn.request("GET", "/get2/id+name/radio/json/?n=all")
		response = conn.getresponse()
		data = response.read().decode()
		conn.close()

		return json.loads(data)

	def songs1(self, radio, position):
		conn = http.client.HTTPConnection("api.jamendo.com")
		conn.request("GET", "/get2/radioposition+id+stream/track/json/?radioid={0}&radiolisteneridstr=LISTENERIDSTR&radioposition={1}".format(radio, position))
		response = conn.getresponse()
		data = response.read().decode()

		return json.loads(data)

	def echo(self):
		radios = self.list()

		print("{0:5}  {1}".format("RADIO", "NAME"))
		for r in radios:
			print("{0:5}. {1}".format(r["id"], r["name"]))

	def play(self, radio):
		s = None
		p = 0

		self.radio = radio
		while True:
			# Hacer varias peticiones y recoger una lista de canciones a reproducir para no estar arrancado el proceso mpg123 continuamente
			self.songs = []
			self.songs.extend(self.songs1(self.radio, p))
			p = max([x["radioposition"] for x in self.songs]) + 1
			self.songs.extend(self.songs1(self.radio, p))
			p = max([x["radioposition"] for x in self.songs]) + 1
			self.songs.extend(self.songs1(self.radio, p))
			p = max([x["radioposition"] for x in self.songs]) + 1

			s = [x["stream"] for x in self.songs]
			
			# Iniciar el reproductor real con el grupo de canciones a reproducir
			args = ["mpg123", "-q"]
			args.extend(s)
			subprocess.call(args, stdout=open('/dev/null', 'w'), stderr=subprocess.STDOUT)

if __name__ == '__main__':
	parser = optparse.OptionParser(usage = 'usage: JamendoRadioPlayer.py [options]')
	parser.add_option('-e', '--echo', action='store_true', help='List Jamendo radios')
	parser.add_option('-r', '--radio', action='store', help='Play a Jamendo radio')
	(options, args) = parser.parse_args()

	if options.echo:
		player = JamendoRadioPlayer()
		player.echo()
	elif options.radio:
		player = JamendoRadioPlayer()
		player.play(options.radio)
	else:		
		parser.print_help()
