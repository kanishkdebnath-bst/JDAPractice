package com.kanishk.SpringKotlin.Controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.kanishk.SpringKotlin.Model.Bank
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    var mockMvc: MockMvc,
    var objectMapper: ObjectMapper
) {

    val baseUrl = "/api/banks"


    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks`() {
            //when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath(expression = "$[0].account_number") { value("123") }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `should give bank with specific account number`() {
            //given
            val accountNumber = 123

            //when
            mockMvc.get("$baseUrl/$accountNumber")

                //then
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") { value("123.0") }
                    jsonPath("$.default_transaction_fee") { value("12") }
                }
        }

        @Test
        fun `should return NOT FOUND when account number doesn't exist`() {
            //given
            val accountNumber = "doesnt_exist"

            //when
            mockMvc.get("$baseUrl/$accountNumber")

                //then
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("POST api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewBank {

        @Test
        fun `should add bank with given details`() {
            //given
            val newBank = Bank("new123", 123.00, 69)

            //when
            val post = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            //then
            post.andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.account_number") { value("new123") }
                    jsonPath("$.trust") { value("123.0") }
                    jsonPath("$.default_transaction_fee") { value("69") }
                }

            mockMvc.get("$baseUrl/${newBank.accountNumber}")
                .andDo { print() }
                .andExpect {
                    content { json(objectMapper.writeValueAsString(newBank)) }
                }
        }

        @Test
        fun `should return BAD REQUEST while adding invalid bank details`() {
            //given
            val newBank = Bank("123", 123.00, 69)

            //when
            val post = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            //then
            post.andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }

    @Nested
    @DisplayName("Patch /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchBank {

        @Test
        fun `should  update an existing bank`() {
            //given
            val updatedBank: Bank = Bank("123", 123.00, 12)

            //when
            val performPatch = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }

            //then
            performPatch.andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedBank))
                    }
                }

            mockMvc.get("$baseUrl/${updatedBank.accountNumber}")
                .andDo { print() }
                .andExpect {
                    content { json(objectMapper.writeValueAsString(updatedBank)) }
                }
        }

        @Test
        fun `should return a BAD REQUEST when no given account number is in the database`() {
            //given
            val invalidBank : Bank = Bank("account_doesntexist", 123.0, 12)

            //when
            mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            //then
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }

    }

    @Nested
    @DisplayName("DELETE /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class deleteGivenBank {
        @Test
        @DirtiesContext
        fun `should delete the bank from the database`() {
            //given
            val accountNumber: String = "123"

            //when
            mockMvc.delete("$baseUrl/$accountNumber")

            //then
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }

            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }

        @Test
        fun `should return NOT FOUND if invalid account number is given`() {
            //given
            val accountNumber : String = "account_not_exist"


            //when
            mockMvc.delete("$baseUrl/$accountNumber")

                //then
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

}