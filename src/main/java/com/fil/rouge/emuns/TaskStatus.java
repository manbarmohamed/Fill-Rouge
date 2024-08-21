package com.fil.rouge.emuns;

public enum TaskStatus {

    PENDING,       // Le client a ajouté une tâche qui est en attente de révision par un administrateur
    ACCEPTED,      // L'administrateur a révisé et accepté la tâche
    IN_PROGRESS,   // La tâche est en cours de réalisation par un travailleur
    COMPLETED,     // La tâche a été terminée par le travailleur
    REJECTED       // L'administrateur a révisé et rejeté la tâche
}
