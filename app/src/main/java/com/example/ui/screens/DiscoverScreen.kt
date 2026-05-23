package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.GlassCard
import com.example.ui.theme.MintGlass
import com.example.ui.theme.SkyLavender
import com.example.ui.theme.SoftCoral
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import kotlin.random.Random

@Composable
fun DiscoverScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(32.dp))
        
        // Search Bar
        GlassCard(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(56.dp),
            shape = RoundedCornerShape(9999.dp),
            blurRadius = 40f
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Search, contentDescription = "Search", tint = TextSecondary)
                Spacer(modifier = Modifier.width(12.dp))
                Text("Search topics, people, places...", color = TextSecondary, fontSize = 16.sp)
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Filled.Mic, contentDescription = "Voice Search", tint = TextSecondary)
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Trending Chips
        val trendingTopics = listOf("Generative Art", "Sunset Vibes", "Lofi Beats", "Street Style", "Weekend Getaway")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(trendingTopics.size) { index ->
                GlassCard(
                    modifier = Modifier.height(40.dp),
                    shape = RoundedCornerShape(9999.dp),
                    opacity = 0.8f
                ) {
                    Box(modifier = Modifier.padding(horizontal = 20.dp), contentAlignment = Alignment.Center) {
                        Text(trendingTopics[index], color = TextPrimary, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Masonry Grid
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 120.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalItemSpacing = 12.dp,
            modifier = Modifier.fillMaxSize()
        ) {
            items(20) { index ->
                val height = remember { Random.nextInt(150, 300).dp }
                val colors = listOf(SoftCoral, SkyLavender, MintGlass)
                val bgColor = remember { colors.random().copy(alpha = 0.3f) }
                
                GlassCard(
                    modifier = Modifier.fillMaxWidth().height(height),
                    shape = RoundedCornerShape(16.dp),
                    opacity = 0.5f
                ) {
                    Box(modifier = Modifier.fillMaxSize().background(bgColor))
                }
            }
        }
    }
}

