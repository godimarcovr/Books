package pigiadibooks.admincreation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pigiadibooks.model.FakePosPrivateUserModel;
import pigiadibooks.pagesutil.FakePosPrivateUserEdit;

public class AccountCreationPanel extends JPanel {
	
	JTextField user,pass,email,name,surname,latitude,longitude,role;
	JLabel result;
	
	public AccountCreationPanel(){
		this.setLayout(new GridLayout(9, 2));
		
		this.add(new JLabel("Username: "));
		this.user=new JTextField(20);
		this.add(this.user);
		
		this.add(new JLabel("Password: "));
		this.pass=new JTextField(20);
		this.add(this.pass);
		
		this.add(new JLabel("Email: "));
		this.email=new JTextField(20);
		this.add(this.email);
		
		this.add(new JLabel("Nome: "));
		this.name=new JTextField(20);
		this.add(this.name);
		
		this.add(new JLabel("Cognome: "));
		this.surname=new JTextField(20);
		this.add(this.surname);
		
		this.add(new JLabel("Latitudine: "));
		this.latitude=new JTextField(20);
		this.add(this.latitude);
		
		this.add(new JLabel("Longitudine: "));
		this.longitude=new JTextField(20);
		this.add(this.longitude);
		
		this.add(new JLabel("Ruolo: "));
		this.role=new JTextField(20);
		this.add(this.role);
		
		this.result=new JLabel("");
		this.add(this.result);
		
		JButton conf=new JButton("Inserisci utente!");
		conf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FakePosPrivateUserModel fpum=new FakePosPrivateUserModel();
				if(user.getText()==null || user.getText().equals("") ||
						pass.getText()==null || pass.getText().equals("") ||
						email.getText()==null || email.getText().equals("")){
					result.setText("Inserimento fallito a causa di valori nulli");
				}
				else{
					fpum.setCognome(surname.getText());
					fpum.setNome(name.getText());
					fpum.setUsername(user.getText());
					fpum.setPassword(pass.getText());
					fpum.setEmail(email.getText());
					fpum.setxPos(Float.parseFloat(latitude.getText()));
					fpum.setyPos(Float.parseFloat(longitude.getText()));
					fpum.setRuolo(role.getText());
					FakePosPrivateUserEdit refg=new FakePosPrivateUserEdit(user.getText());
					try {
						refg.insertUser(fpum);
						result.setText("Inserimento con successo!");
					} catch (Exception ex) {
						ex.printStackTrace();
						result.setText("Inserimento fallito!");
					}
				}
				
				
			}
		});
		this.add(conf);
	}
}
