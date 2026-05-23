package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.GlassCard
import com.example.ui.theme.MintGlass
import com.example.ui.theme.SkyLavender
import com.example.ui.theme.SoftCoral
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun ChatsScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(32.dp))
        Text("Messages", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Spacer(modifier = Modifier.height(24.dp))
        
        // Active Stories / Online Row
        Row {
            val colors = listOf(SoftCoral, SkyLavender, MintGlass)
            for (i in 0..4) {
                Box(modifier = Modifier.padding(end = 16.dp)) {
                    Box(modifier = Modifier.size(64.dp).clip(CircleShape).background(colors[i % 3].copy(alpha = 0.5f)))
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Chat List
        for (i in 1..8) {
            GlassCard(
                modifier = Modifier.fillMaxWidth().height(80.dp).padding(bottom = 12.dp),
                shape = RoundedCornerShape(20.dp),
                opacity = 0.5f
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(48.dp).clip(CircleShape).background(SkyLavender.copy(alpha=0.3f)))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Friend $i", fontWeight = FontWeight.Bold, color = TextPrimary)
                        Text("Sent a message • ${i}h", color = TextSecondary, fontSize = 14.sp)
                    }
                    if (i < 3) {
                        Box(modifier = Modifier.size(12.dp).clip(CircleShape).background(SoftCoral))
                    }
                }
            }
        }
    }
}

