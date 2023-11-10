package com.example.tbcshemajamebeli2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tbcshemajamebeli2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val anagrams = mutableListOf<String>()

        binding.btnSave.setOnClickListener {
            if(binding.etEnterAnagram.text.toString().trim().isEmpty()){
                binding.tvErrorEnterAnagram.text = "Enter a value in this field"
                return@setOnClickListener
            }
            anagrams.add(binding.etEnterAnagram.text.toString())
        }

        binding.btnOutput.setOnClickListener {
            val batchAnagrams = findAnagrams(anagrams)
            val anagramCount = batchAnagrams.size

            val generateText = "anagram count : " + anagramCount.toString()
            binding.tvAnagramCount.text = generateText
        }
    }

    fun findAnagrams(words: MutableList<String>): List<List<String>> {
        val groupedAnagrams = mutableMapOf<String, MutableList<String>>()

        for (word in words) {
            val sortedWord = word.toCharArray().sorted().joinToString("")
            groupedAnagrams.getOrPut(sortedWord) { mutableListOf() }.add(word)
        }

        return groupedAnagrams.values.toList()
    }
}