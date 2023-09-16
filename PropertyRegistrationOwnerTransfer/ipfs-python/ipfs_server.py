from flask import Flask
import pickle
import os.path
import ipfshttpclient
from pathlib import Path
import json
app = Flask(__name__)
#input filename no extension

ipfs_url = '/ip4/127.0.0.1/tcp/5001/http'
filepath = 'files/';


def putfile(filename):
    print("Connecting to IPFS Network...")
    conn = ipfshttpclient.connect(ipfs_url)
    print("Connected!")
    res = conn.add(filename)
    print("Block Created!")
    print("Hash: ")
    print(res)
    return res
    
def getipfsfile(filehash):
    print("Connecting to IPFS Network...")
    conn = ipfshttpclient.connect(ipfs_url)
    print("Connected!")
    resp = conn.cat(filehash)
    resp = resp.decode('utf-8')
    print("Response: ")
    print(resp)
    return resp



@app.route('/')
def index():
    return "No Data Forwarded"

@app.route('/addblock/<datatoadd>')
def saveFile(datatoadd):
    print(datatoadd)
    with open('temp.txt', 'w') as f:
        f.write(datatoadd)
    result = putfile('temp.txt')
    return "{}".format(result)
        
@app.route('/getblock/<filehash>')
def getFile(filehash):
    print("Received hash: ")
    print(filehash)
    result = getipfsfile(filehash)
    return "{}".format(result)

if __name__ == '__main__':
    app.run(host= '0.0.0.0')




    
#getile()
