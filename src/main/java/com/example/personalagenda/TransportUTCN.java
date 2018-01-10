package com.example.personalagenda;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

// clasa creeare , modificare Memo
public class TransportUTCN extends MemoGeneralKeepLayout implements
		OnClickListener {

	Spinner spinnerLinii;
	Button meniuPrincipal;
	TextView perechea1wd, perechea2wd, perechea1wS,perechea1wD, perechea2wS, perechea2wD, distanta, durata, sursa1, sursa2, destinatie1, destinatie2;
	TransportUTCNHandlerSQL entry = new TransportUTCNHandlerSQL(TransportUTCN.this);
	ImageView imagineTip;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transport_utcluj);
		initButoane();	
		meniuPrincipal.setOnClickListener(this);
		initDB();
		
		//se selecteaza ruta
		spinnerLinii.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String opt = spinnerLinii.getSelectedItem().toString();
				
				// TODO Auto-generated method stub
				if (opt.equals("Linia")) {
					
					imagineTip.setImageResource(R.drawable.ic_launcher);
					distanta.setText("Distanta");
					durata.setText("Durata");
					sursa1.setText("Capat 1");
					destinatie1.setText("Capat 2");
					sursa2.setText("Capat 1");
					destinatie2.setText("Capat 2");
					perechea1wd.setText("");
					perechea1wS.setText("");
					perechea1wD.setText("");
					perechea2wd.setText("");
					perechea2wS.setText("");
					perechea2wD.setText("");
				}
				if (opt.equals("35")) {
					entry.open();
					String type35 = entry.getBusType("35", TransportUTCNHandlerSQL.KEY_LINE);
					String source35 = entry.getsSource("35", TransportUTCNHandlerSQL.KEY_LINE);
					String dest35 = entry.getDestination("35", TransportUTCNHandlerSQL.KEY_LINE);
					String duration35 = entry.getDuration("35", TransportUTCNHandlerSQL.KEY_LINE);
					String distance35 = entry.getDistance("35", TransportUTCNHandlerSQL.KEY_LINE);
					String hours35wd1 = entry.getWDHours1("35", TransportUTCNHandlerSQL.KEY_LINE).replaceAll("	", "\n-------------------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String[] hours35w1 = entry.getWHours1("35", TransportUTCNHandlerSQL.KEY_LINE).split("[!]");
					String p1ws = hours35w1[0].replaceAll("	", "\n--------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String p1wd = hours35w1[1].replaceAll("	", "\n--------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String hours35wd2 = entry.getWDHours2("35", TransportUTCNHandlerSQL.KEY_LINE).replaceAll("	", "\n-------------------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String[] hours35w2 = entry.getWHours2("35", TransportUTCNHandlerSQL.KEY_LINE).split("[!]");
					String p2ws = hours35w2[0].replaceAll("	", "\n--------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String p2wd = hours35w2[1].replaceAll("	", "\n--------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					entry.close();
					
					if(type35.equals("autobuz")){
						imagineTip.setImageResource(R.drawable.transport_autobuz);
					}
					else
						imagineTip.setImageResource(R.drawable.ic_launcher);
					distanta.setText(distance35);
					durata.setText(duration35);
					sursa1.setText(source35);
					destinatie1.setText(dest35);
					sursa2.setText(dest35);
					destinatie2.setText(source35);
					perechea1wd.setText(hours35wd1);
					perechea1wS.setText(p1ws);
					perechea1wD.setText(p1wd);
					perechea2wd.setText(hours35wd2);
					perechea2wS.setText(p2ws);
					perechea2wD.setText(p2wd);


				}
				if (opt.equals("43P")) {
					entry.open();
					String type43P = entry.getBusType("43P", TransportUTCNHandlerSQL.KEY_LINE);
					String source43P = entry.getsSource("43P", TransportUTCNHandlerSQL.KEY_LINE);
					String dest43P = entry.getDestination("43P", TransportUTCNHandlerSQL.KEY_LINE);
					String distance43P = entry.getDistance("43P", TransportUTCNHandlerSQL.KEY_LINE);
					String duration43P = entry.getDuration("43P", TransportUTCNHandlerSQL.KEY_LINE);
					String hours35wd1 = entry.getWDHours1("43P", TransportUTCNHandlerSQL.KEY_LINE).replaceAll("	", "\n-------------------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String hours35w1 = entry.getWDHours1("43P", TransportUTCNHandlerSQL.KEY_LINE).replaceAll("	", "\n--------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String hours35wd2 = entry.getWHours2("43P", TransportUTCNHandlerSQL.KEY_LINE).replaceAll("	", "\n-------------------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String hours35w2 = entry.getWHours2("43P", TransportUTCNHandlerSQL.KEY_LINE).replaceAll("	", "\n--------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					
					entry.close();
					if(type43P.equals("autobuz")){
						imagineTip.setImageResource(R.drawable.transport_autobuz);
					}
					else
						imagineTip.setImageResource(R.drawable.ic_launcher);
					distanta.setText(distance43P);
					durata.setText(duration43P);
					sursa1.setText(source43P);
					destinatie1.setText(dest43P);
					sursa2.setText(dest43P);
					destinatie2.setText(source43P);
					perechea1wd.setText(hours35wd1);
					perechea1wS.setText(hours35w1);
					perechea1wD.setText(hours35w1);
					perechea2wd.setText(hours35wd2);
					perechea2wS.setText(hours35w2);
					perechea2wD.setText(hours35w2);
				}
				if (opt.equals("25")) {
					entry.open();
					String type25 = entry.getBusType("25", TransportUTCNHandlerSQL.KEY_LINE);
					String source25 = entry.getsSource("25", TransportUTCNHandlerSQL.KEY_LINE);
					String dest25 = entry.getDestination("25", TransportUTCNHandlerSQL.KEY_LINE);
					String duration25 = entry.getDuration("25", TransportUTCNHandlerSQL.KEY_LINE);
					String distance25 = entry.getDistance("25", TransportUTCNHandlerSQL.KEY_LINE);
					String hours25wd1 = entry.getWDHours1("25", TransportUTCNHandlerSQL.KEY_LINE).replaceAll("	", "\n-------------------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String[] hours25w1 = entry.getWHours1("25", TransportUTCNHandlerSQL.KEY_LINE).split("[!]");
					String p1ws = hours25w1[0].replaceAll("	", "\n--------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String p1wd = hours25w1[1].replaceAll("	", "\n--------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String hours25wd2 = entry.getWDHours2("25", TransportUTCNHandlerSQL.KEY_LINE).replaceAll("	", "\n-------------------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String[] hours25w2 = entry.getWHours2("25", TransportUTCNHandlerSQL.KEY_LINE).split("[!]");
					String p2ws = hours25w2[0].replaceAll("	", "\n--------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String p2wd = hours25w2[1].replaceAll("	", "\n--------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					entry.close();
					if(type25.equals("trolebuz")){
						imagineTip.setImageResource(R.drawable.transport_trolebuz);
					}
					else
						imagineTip.setImageResource(R.drawable.ic_launcher);
					distanta.setText(distance25);
					durata.setText(duration25);
					sursa1.setText(source25);
					destinatie1.setText(dest25);
					sursa2.setText(dest25);
					destinatie2.setText(source25);
					perechea1wd.setText(hours25wd1);
					perechea1wS.setText(p1ws);
					perechea1wD.setText(p1wd);
					perechea2wd.setText(hours25wd2);
					perechea2wS.setText(p2ws);
					perechea2wD.setText(p2wd);
				}
				if (opt.equals("102")) {
					entry.open();
					String type102 = entry.getBusType("102", TransportUTCNHandlerSQL.KEY_LINE);
					String source102 = entry.getsSource("102", TransportUTCNHandlerSQL.KEY_LINE);
					String dest102 = entry.getDestination("102", TransportUTCNHandlerSQL.KEY_LINE);
					String duration102 = entry.getDuration("102", TransportUTCNHandlerSQL.KEY_LINE);
					String distance102 = entry.getDistance("102", TransportUTCNHandlerSQL.KEY_LINE);
					String hours102wd1 = entry.getWDHours1("102", TransportUTCNHandlerSQL.KEY_LINE).replaceAll("	", "\n-------------------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String hours102w1 = entry.getWHours1("102", TransportUTCNHandlerSQL.KEY_LINE);
					String hours102wd2 = entry.getWDHours2("102", TransportUTCNHandlerSQL.KEY_LINE).replaceAll("	", "\n-------------------\n").replaceAll("[.]", ":").replaceAll("[,]",":");
					String hours102w2 = entry.getWHours2("102", TransportUTCNHandlerSQL.KEY_LINE);
					entry.close();
					if(type102.equals("tramvai")){
						imagineTip.setImageResource(R.drawable.transport_tramvai);
					}
					else
						imagineTip.setImageResource(R.drawable.ic_launcher);
					distanta.setText(distance102);
					durata.setText(duration102);
					sursa1.setText(source102);
					destinatie1.setText(dest102);
					sursa2.setText(dest102);
					destinatie2.setText(source102);
					perechea1wd.setText(hours102wd1);
					perechea1wS.setText(hours102w1);
					perechea1wD.setText(hours102w1);
					perechea2wd.setText(hours102wd2);
					perechea2wS.setText(hours102w2);
					perechea2wD.setText(hours102w2);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {	
	case (R.id.transport_utcluj_meniu_principal):
		entry.close();
		entry.delete();
		Intent i = new Intent("com.example.personalagenda.MENU");
		startActivity(i);
		break;
	}
	}
	
	public void initDB(){
		entry.open();
		// linie, tip, sursa, destinatie, distanta, durata, zile de lucru capat 1, weekend capat 1, zile de lucru capat 2, weekend capat2
		entry.createEntry("35", "autobuz", "Cart. Zorilor", "P-ta Garii", "11.1 km", "46 min", "5,01	5,08	5,16	5,24	5,31	5,40	5,46	5,54	6,00	6,08	6,14	6,22	6,28	6,34	6.41	6.48	6.55	7.02	7.08	7.14	7.20	7.26	7.32	7.37	7.43	7.50	7.57	8.03	8.10	8.16	8.22	8.29	8.35	8.42	8.48	8.55	9.01	9.07	9.14	9.20	9.27	9.33	9.39	9.45	9.52	9.58	10.05	10.12	10.18	10.25	10.31	10.38	10.44	10.51	10.58	11.04	11.11	11.17	11.23	11.30	11.36	11.43	11.49	11.56	12.02	12.08	12.14	12.21	12.28	12.34	12.41	12.47	12.53	12.59	13.05	13.12	13.19	13.25	13.32	13.39	13.45	13.52	13.58	14.05	14.11	14.17	14.24	14.30	14.36	14.42	14.49	14.56	15.02	15.09	15.15	15.21	15.27	15.33	15.40	15.47	15.53	16.00	16.06	16.12	16.19	16.25	16.32	16.38	16.45	16.52	16.58	17.05	17.11	17.17	17.24	17.30	17.37	17.44	17.50	17.56	18.02	18.08	18.15	18.22	18.28	18.35	18.42	18.48	18.54	19.00	19.06	19,13	19,18	19,24	19,30	19,36	19,42	19,48	19,54	20.00	20.07	20.13	20.19	20.26	20.33	20.41	20.47	20.54	21.02	21.11	21.20	21.28	21.37	21.45	21.53	22.01	22.09	22.18	22.26	22.35	22.46	22.56", "5,20	5,28	5,39	5,50	6,00	6,09	6,18	6,27	6,36	6,45	6,54	7,00	7,06	7.14	7.22	7.30	7.37	7.46	7,54	8,02	8,10	8,18	8.27	8.35	8.44	8.52	9,00	9,09	9,17	9,26	9.34	9.42	9.51	9.59	10,08	10,16	10,24	10,33	10,41	10,50	10,58	11,06	11,15	11.23	11.32	11.40	11.48	11,57	12,05	12,14	12,22	12.30	12.38	12.47	12.56	13,04	13,12	13,21	13.29	13.37	13.46	13.54	14,03	14,11	14,19	14,28	14,36	14,45	14,53	15,01	15,10	15,18	15.27	15.35	15.43	15.52	16,00	16,09	16,17	16.25	16.34	16.42	16.51	16.59	17,07	17,16	17,24	17.33	17.42	17.49	17.58	18,06	18,15	18,23	18,31	18,39	18,48	18,56	19.05	19,13	19.21	19.30	19.38	19.46	19,54	20,02	20,10	20,18	20.26	20.34	20.43	20.50	20.58	21,06	21,14	21,22	21.30	21.37	21.46	21.54	22,02	22,10	22,20	22,29	22,38 ! 6,03	6,14	6,25	6,35	6,45	6,54	7,03	7.12	7.21	7.30	7.37	7.46	7,54	8,02	8,10	8,18	8.27	8.35	8.44	8.52	9,00	9,09	9,17	9,26	9.34	9.42	9.51	9.59	10,08	10,16	10,24	10,33	10,41	10,50	10,58	11,06	11,15	11.23	11.32	11.40	11.48	11,57	12,05	12,14	12,22	12.30	12.38	12.47	12.56	13,04	13,12	13,21	13.29	13.37	13.46	13.54	14,03	14,11	14,19	14,28	14,36	14,45	14,53	15,01	15,10	15,18	15.27	15.35	15.43	15.52	16,00	16,09	16,17	16.25	16.34	16.42	16.51	16.59	17,07	17,16	17,24	17.33	17.42	17.49	17.58	18,06	18,15	18,23	18,31	18,39	18,48	18,56	19.05	19,13	19.21	19.30	19.38	19.46	19,54	20,02	20,10	20,18	20.26	20.34	20.43	20.50	20.58	21,06	21,14	21,22	21.30	21.37	21.46	21.54	22,02	22,10	22,20	22,29	22,38", "5,00	5,09	5,18	5,26	5,34	5,42	5,50	5,58	6,05	6,12	6,19	6,26	6,33	6,40	6,47	6,53	7.00	7.07	7.14	7.21	7.27	7.33	7.40	7.46	7.52	7.58	8.04	8.11	8.18	8.24	8.31	8.37	8.43	8.50	8.56	9.03	9.09	9.16	9.22	9.28	9.35	9.41	9.48	9.54	10.00	10.06	10.13	10.19	10.26	10.33	10.39	10.46	10.52	10.59	11.05	11.12	11.19	11.25	11.32	11.38	11.44	11.51	11.57	12.04	12.10	12.17	12.23	12.29	12.35	12.42	12.49	12.55	13.02	13.08	13.14	13.20	13.26	13.33	13.40	13.46	13.53	14.00	14.06	14.13	14.19	14.26	14.32	14.38	14.45	14.51	14.57	15.03	15.10	15.17	15.23	15.30	15.36	15.42	15.48	15.54	16.01	16.08	16.14	16.21	16.27	16.33	16.40	16.46	16.53	16.59	17.06	17.13	17.19	17.26	17.32	17.38	17.45	17.51	17.58	18.05	18.11	18.17	18.23	18.29	18.36	18.43	18.49	18.56	19.03	19.09	19.15	19.21	19.27	19,33	19,38	19,44	19,50	19,56	20,02	20.08	20.14	20,20	20,27	20,33	20,39	20.46	20.53	21.00	21.07	21.14	21.22	21.31	21.40	21.48	21.57	22.05	22.13	22.21	22.29	22.37	22.47	22.57", "5,23	5,30	5,37	5,45	5,56	6,07	6,17	6.26	6,35	6,44	6,53	7,02	7,11	7,18	7.25	7.32	7.40	7.48	7.56	8.04	8.12	8.20	8.28	8.37	8.46	8.54	9,03	9.11	9.19	9.28	9.36	9.45	9.53	10.01	10.10	10.18	10.27	10.35	10.43	10.52	11.00	11.09	11.17	11.25	11.34	11.42	11.51	11.59	12.07	12.16	12.24	12.33	12.41	12.49	12.57	13,06	13.15	13.23	13.31	13.40	13.48	13.56	14.05	14.13	14.22	14.30	14.38	14.47	14.55	15.04	15.12	15.20	15.29	15.37	15.46	15.54	16.02	16.11	16.19	16.28	16.36	16.44	16.53	17,01	17.10	17.18	17.26	17.35	17.43	17.52	18.01	18.08	18.17	18.25	18.34	18.42	18.50	18.58	19.07	19.15	19.24	19.32	19.40	19.48	19.56	20.04	20.12	20.20	20.28	20.36	20.44	20.52	21,00	21.08	21.16	21.24	21.32	21.40	21.48	21.56	22.04	22.12	22.20	22.30	22.40 ! 6.03	6.13	6.23	6,33	6,43	6,53	7,02	7,11	7.20	7.29	7.38	7.47	7.56	8.04	8.12	8.20	8.28	8.37	8.46	8.54	9,03	9.11	9.19	9.28	9.36	9.45	9.53	10.01	10.10	10.18	10.27	10.35	10.43	10.52	11.00	11.09	11.17	11.25	11.34	11.42	11.51	11.59	12.07	12.16	12.24	12.33	12.41	12.49	12.57	13,06	13.15	13.23	13.31	13.40	13.48	13.56	14.05	14.13	14.22	14.30	14.38	14.47	14.55	15.04	15.12	15.20	15.29	15.37	15.46	15.54	16.02	16.11	16.19	16.28	16.36	16.44	16.53	17,01	17.10	17.18	17.26	17.35	17.43	17.52	18.01	18.08	18.17	18.25	18.34	18.42	18.50	18.58	19.07	19.15	19.24	19.32	19.40	19.48	19.56	20.04	20.12	20.20	20.28	20.36	20.44	20.52	21,00	21.08	21.16	21.24	21.32	21.40	21.48	21.56	22.04	22.12	22.20	22.30	22.40");
		entry.createEntry("43P", "autobuz", "Cart. Zorilor", "Polus Center", "14.4 km", "60 min", "8,35	9,05	9,35	10,05	10,35	11,05	11,35	12,05	12,35	13,05	13,35	14,05	14,35	15,05	15,35	16,05	16,35	17,05	17,35	18,05	18,35	19,05	19,35	20,05	20,35	21,05	21,35	22,40", "9,10	9,40	10,10	10,40	11,10	11,40	12,10	12,40	13,10	13,40	14,10	14,40	15,10	15,40	16,10	16,40	17,10	17,40	18,10	18,40	19,10	19,40	20,10	20,40	21,10	21,40	22,15	23,00", "8,35	9,05	9,35	10,05	10,35	11,05	11,35	12,05	12,35	13,05	13,35	14,05	14,35	15,05	15,35	16,05	16,35	17,05	17,35	18,05	18,35	19,05	19,35	20,05	20,35	21,05	21,35	22,40", "9,10	9,40	10,10	10,40	11,10	11,40	12,10	12,40	13,10	13,40	14,10	14,40	15,10	15,40	16,10	16,40	17,10	17,40	18,10	18,40	19,10	19,40	20,10	20,40	21,10	21,40	22,15	23,00");
		entry.createEntry("25", "trolebuz", "Str. Bucium", "Str. Unirii", "17 km", "71 min", "5.03	5.13	5.23	5.33	5.43	5.53	6.03	6,14	6,22	6.33	6.43	6.53	7.03	7.13	7,22	7.27	7.35	7.45	7.55	8.05	8.15	8.25	8.35	8.44	8.53	9.03	9.13	9.23	9.33	9.42	9.52	10.02	10.11	10.21	10.32	10.41	10.50	11.00	11.10	11.19	11.29	11.39	11.49	11.58	12.08	12.17	12.27	12.37	12.47	12.57	13.06	13.16	13.26	13.36	13.46	13.56	14.06	14.16	14.26	14.36	14.46	14.56	15.06	15.16	15.26	15.36	15.46	15.56	16.06	16.16	16.26	16.36	16.46	16.56	17.06	17.16	17.27	17.36	17.46	17.56	18.06	18.14	18.25	18.34	18.44	18.52	19.03	19.13	19.22	19.33	19.42	19.52	20,02	20,13	20,21	20,28	20,39	20.50	21.01	21.13	21.25	21.37	21.49	22.01	22.13	22.26	22.40", "5.25	5.37	5.49	6.01	6.13	6.25	6.37	6.49	7.01	7.13	7.25	7.37	7.49	8.00	8.10	8.20	8.31	8.42	8.53	9.04	9.15	9.26	9.37	9.48	9.59	10.10	10.21	10.32	10.43	10.54	11.05	11.16	11.27	11.38	11.49	12.00	12.11	12.22	12.33	12.44	12.55	13.06	13.17	13.28	13.39	13.50	14.01	14.11	14.22	14.33	14.44	14.55	15.06	15.16	15.27	15.38	15.49	16.00	16.11	16.21	16.32	16.43	16.54	17.05	17.16	17.27	17.37	17.48	17.59	18.10	18.21	18.31	18.42	18.53	19.04	19.15	19.26	19.37	19,48	19,58	20,09	20,19	20,30	20,41	20,51	21.02	21,12	21,23	21,33	21.45	22.00	22.15	22.30	! 5.55	6.07	6.19	6.31	6.43	6.55	7.07	7.19	7.31	7.43	7.55	8.05	8.16	8.26	8.37	8.47	8.58	9.08	9.19	9.29	9.40	9.50	10.01	10.11	10.22	10.32	10.43	10.53	11.04	11.14	11.25	11.36	11.47	11.58	12.08	12.19	12.29	12.40	12.50	13.01	13.11	13.22	13.33	13.43	13.54	14.04	14.15	14.25	14.36	14.47	14.58	15.09	15.19	15.30	15.40	15.51	16.01	16.12	16.22	16.33	16.43	16.54	17.04	17.15	17.25	17.36	17.46	17.57	18.07	18.18	18.28	18.39	18.49	19.00	19,11	19,21	19,33	19,44	19,54	20,04	20,14	20,24	20,34	20,44	20,54	21,04	21,15	21,26	21,37	21,48	22.00	22.15	22.30", "5.02	5.16	5.30	5.42	5.52	6.02	6.12	6.22	6.32	6.42	6,50	6,55	7.02	7.12	7.22	7.32	7.42	7,51	7.58	8.06	8.15	8.25	8.35	8.45	8.55	9.05	9.14	9.23	9.33	9.43	9.53	10.03	10.12	10.22	10.32	10.41	10.51	11.01	11.11	11.20	11.30	11.40	11.49	11.59	12.09	12.19	12.28	12.38	12.47	12.57	13.07	13.17	13.27	13.36	13.46	13.56	14.06	14.16	14.26	14.36	14.46	14.56	15.06	15.16	15.26	15.36	15.46	15.56	16.06	16.16	16.26	16.36	16.46	16.56	17.06	17.16	17.26	17.36	17.46	17.56	18.06	18.16	18.26	18.36	18.45	18.55	19.04	19.14	19.23	19.33	19.43	19.52	20.02	20.12	20.22	20,33	20,45	20,57	21,09	21.20	21.30	21.42	21.54	22.06	22.18	22.30	22.42", "5.30	5.42	5.54	6.06	6.18	6.30	6.42	6.54	7.06	7.18	7.30	7.42	7.54	8.06	8.18	8.30	8.41	8.51	9.02	9.13	9.24	9.35	9.46	9.57	10.08	10.19	10.30	10.41	10.52	11.03	11.14	11.25	11.36	11.47	11.58	12.09	12.20	12.31	12.42	12.53	13.04	13.15	13.26	13.37	13.48	13.59	14.10	14.21	14.32	14.43	14.53	15.04	15.15	15.26	15.37	15.47	15.58	16.09	16.20	16.31	16.42	16.52	17.03	17.14	17.25	17.36	17.47	17.58	18.08	18.19	18.29	18.40	18.51	19.01	19.12	19.23	19.34	19.45	19.56	20.07	20,18	20,28	20,39	20,49	21,00	21,11	21,21	21.32	21,42	21,52	22,02	22.14	22.28	! 6.00	6.12	6.24	6.36	6.48	7.00	7.12	7.24	7.36	7.48	8.00	8.12	8.24	8.36	8.46	8.57	9.08	9.18	9.29	9.39	9.50	10.00	10.11	10.21	10.32	10.42	10.53	11.03	11.14	11.24	11.35	11.45	11.56	12.07	12.18	12.29	12.39	12.50	13.00	13.11	13.21	13.32	13.42	13.53	14.04	14.14	14.25	14.35	14.46	14.56	15.07	15.18	15.29	15.40	15.50	16.01	16.11	16.22	16.32	16.43	16.53	17.04	17.14	17.25	17.35	17.46	17.56	18.07	18.17	18.28	18.38	18.49	18.59	19.10	19.20	19.31	19,42	19,52	20,03	20,14	20,24	20,34	20,44	20,54	21,04	21,14	21,24	21,34	21.45	21,55	22,06	22,17	22.28");
		entry.createEntry("102", "tramvai", "B-dul Muncii", "Str. Bucium", "23.7 km", "81 min", "4,52	5,01	5,10	5,17	5,23	5,31	5,40	5,50	6,00	6,10	6,20	6,29	6,38	6,48	6,56	7,04	7,12	7,20	7,29	7,38	7,48	7,58	8,08	8,17	8,33	12.46	12.55	13.05	13.15	13.25	13.35	13,45	13,55	14,05	14,15	14,25	14,34	14,43	14,51	14,59	15,08	15,17	15,26	15,35	15,44	15,53	16,02	16,11	16,20	16,30	16,39	16,47	17,04	17,19	20,48	21,03	21,15	21,45	22,05	22,25	22,45", " ", "4,52	5,08	5,20	5,30	5,40	5,50	6,01	6,10	6,15	6,20	6,29	6,38	6,48	6,58	7,08	7,18	7,27	7,36	7,45	7,53	8,01	8,10	8,19	8,28	8,38	8,47	9,01	9,14	12,57	13,10	13,25	13,35	13,45	13,55	14,04	14,10	14,16	14,25	14,35	14,45	14,55	15,05	15,14	15,22	15,30	15,38	15,47	15,56	16,05	16,14	16,23	16,32	16,41	16,50	17,00	17.10	17,18 	17.29	17.43	17.58	21,07	21,26	21,43	21,55	22,08	22,23	22,42", " ");
		entry.close();
		}
	
	public void initButoane(){
		spinnerLinii = (Spinner) findViewById(R.id.transport_utcluj_spinner_linii);
		imagineTip = (ImageView)findViewById(R.id.transport_utcluj_imageView);
		meniuPrincipal = (Button) findViewById(R.id.transport_utcluj_meniu_principal);
		distanta = (TextView)findViewById(R.id.transport_utcluj_tv_distanta);
		durata = (TextView)findViewById(R.id.transport_utcluj_tv_durata);
		sursa1 = (TextView)findViewById(R.id.transport_utcluj_tv_sursa1);
		sursa2 = (TextView)findViewById(R.id.transport_utcluj_tv_sursa2);
		destinatie1 = (TextView)findViewById(R.id.transport_utcluj_tv_dest1);
		destinatie2 = (TextView)findViewById(R.id.transport_utcluj_tv_dest2);
		perechea1wd = (TextView) findViewById(R.id.transport_utcluj_tv_perechea1WeekDays);
		perechea2wd = (TextView) findViewById(R.id.transport_utcluj_tv_perechea2WeekDays);
		perechea1wS = (TextView) findViewById(R.id.transport_utcluj_tv_perechea1WeekendSambata);
		perechea2wS = (TextView) findViewById(R.id.transport_utcluj_tv_perechea2WeekendSambata);
		perechea1wD = (TextView) findViewById(R.id.transport_utcluj_tv_perechea1WeekendDuminica);
		perechea2wD = (TextView) findViewById(R.id.transport_utcluj_tv_perechea2WeekendDuminica);
	}

}
