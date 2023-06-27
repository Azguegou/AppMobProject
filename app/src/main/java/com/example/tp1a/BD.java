package com.example.tp1a;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BD {
    private static final String DATABASE_PATH = "users";

    public static void insertUser(String username, String password) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child(DATABASE_PATH);

        // Generate a unique key for the user
        String userId = reference.push().getKey();

        // Create a User object with the provided data
        User user = new User(userId, username, password, 0);

        // Insert the user into the database
        reference.child(userId).setValue(user)
                .addOnSuccessListener(aVoid -> Log.d("BD", "Utilisateur inséré avec succès dans la base de données !"))
                .addOnFailureListener(e -> Log.e("BD", "Erreur lors de l'insertion de l'utilisateur : " + e.getMessage()));
    }
}