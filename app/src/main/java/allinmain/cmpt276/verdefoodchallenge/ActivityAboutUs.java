<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#fff"
    >
    <TextView
        android:id="@+id/title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/about_us"
        android:textColor="#000"
        android:textSize="24dp"
        android:layout_marginTop="8dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <View
        android:id="@+id/headline"
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:background="#000"
        android:layout_marginTop="10dp"
        app:layout_constraintTop_toBottomOf="@id/title"></View>

    <TextView
        android:layout_width="match_parent"
        android:layout_height="475dp"
        android:layout_margin="20dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="10dp"
        android:layout_marginEnd="8dp"
        android:scrollbars="vertical"
        android:text="@string/about_info"
        android:textColor="#000"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/headline" />


</android.support.constraint.ConstraintLayout>