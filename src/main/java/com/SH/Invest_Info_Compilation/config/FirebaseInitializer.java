package com.SH.Invest_Info_Compilation.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
public class FirebaseInitializer {

    @Bean
    public Boolean firebaseApp() {
        FileInputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream("./ignore/serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("asset-calculator.appspot.com")
                    .build();
            FirebaseApp.initializeApp(options);

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            return false;
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        } catch (IllegalStateException e){
            return false;
        }
    }

    @Bean
    public FirebaseAuth getFirebaseAuth(){
        firebaseApp();

        return FirebaseAuth.getInstance();
    }
    @Bean
    public Firestore getFirestore(){
        firebaseApp();

        return FirestoreClient.getFirestore();
    }
}
