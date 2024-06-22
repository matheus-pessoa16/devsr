import os
import requests
import json
import datetime
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import smtplib
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.image import MIMEImage 

from fastapi import FastAPI

app = FastAPI()



sns.set_theme(style="whitegrid")
sns.set_context("paper")
sns.set(rc={'figure.figsize':(12.7,9.27)})

def format_date(date_str):
    date = date_str.split('-')
    
    return str(date[2]) + '/' + str(date[1]) + '/' + str(date[0])


def send_email(filename,date, mvrv):
    sender_email = "matheus.pessoa16@gmail.com"
    receiver_email = "matheus.pessoa16@gmail.com"

    msg = MIMEMultipart()
    msg['Subject'] = 'Bitcoin Analysis!'
    msg['From'] = sender_email
    msg['To'] = receiver_email

    msgText = MIMEText(f'<h3>Data: <b>{ date }</b></h3><h3>MVRV: <b>{mvrv}</b></h3><br/>', 'html')
    msg.attach(msgText)

    with open(filename, 'rb') as fp:
        img = MIMEImage(fp.read())
        img.add_header('Content-Disposition', 'attachment', filename=filename)
        msg.attach(img)

    try:
      with smtplib.SMTP('smtp.gmail.com', 587) as smtpObj:
        smtpObj.ehlo()
        smtpObj.starttls()
        smtpObj.login("matheus.pessoa16@gmail.com", "imubpsounyrxxabz")
        smtpObj.sendmail(sender_email, receiver_email, msg.as_string())
    except Exception as e:
      print(e)
    finally:
        os.remove(filename)


def get_bitcoin_data():
    url = "https://www.lookintobitcoin.com/django_plotly_dash/app/mvrv_zscore/_dash-update-component"

    payload = json.dumps({
    "output": "chart.figure",
    "outputs": {
        "id": "chart",
        "property": "figure"
    },
    "inputs": [
        {
        "id": "url",
        "property": "pathname",
        "value": "/charts/mvrv-zscore/"
        }
    ],
    "changedPropIds": [
        "url.pathname"
    ]
    })
    headers = {
    'Content-Type': 'application/json'
    }

    response = requests.request("POST", url, headers=headers, data=payload)

    content = response.json()

    content = content['response']

    content = content['chart']

    content = content['figure']
    
    content = content['data'][7]

    return content


def plot_graph(content):
    x_data = content['x']
    y_data = content['y']

    x_data_cpy = list(reversed(x_data[:len(y_data)]))
    y_data_cpy = list(reversed(y_data))

    data = {"MVRV": reversed(y_data_cpy[:30]), "DATE": reversed(x_data_cpy[:30])}

    df = pd.DataFrame(data)

    df['DATE'] = pd.to_datetime(df['DATE'])
    df['DATE'] = df['DATE'].dt.strftime('%d/%m/%Y')

    sns.lineplot(df,x="DATE", y="MVRV" ,palette="tab10", linewidth=2)
    plt.xticks(rotation = 60) # Rotates X-Axis Ticks by 60-degrees

    filename = f"mvrv-{datetime.datetime.now().strftime('%d-%m-%Y')}.png"
    plt.savefig(filename)
    
    return filename, format_date(x_data_cpy[0]), y_data_cpy[0]


def get_data_and_plot():
    response = get_bitcoin_data()
    return plot_graph(response)


@app.get("/getPrice")
def read_root():
    filename, date, mvrv = get_data_and_plot()
    print(filename, date, mvrv)

    send_email(filename, date, mvrv)
    return  json.dumps("Enviado!")
